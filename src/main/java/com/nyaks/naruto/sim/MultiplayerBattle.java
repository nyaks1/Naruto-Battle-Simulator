package main.java.com.nyaks.naruto.sim;

import java.io.*;
import java.net.*;
import java.util.*;
import main.java.com.nyaks.naruto.sim.Jutsu.Jutsu;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;
import main.java.com.nyaks.naruto.sim.GameServer.ClientHandler;

public class MultiplayerBattle implements Runnable {
    private final ClientHandler p1;
    private final ClientHandler p2;
    
    private final Shinobi s1;
    private final Shinobi s2;
    
    private boolean s1Evading = false;
    private boolean s2Evading = false;
    private boolean lastActionWasSubstitution = false;

    public MultiplayerBattle(ClientHandler p1, ClientHandler p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.s1 = p1.getShinobi();
        this.s2 = p2.getShinobi();
        
        // Full restore stats
        s1.setHealth(s1.getMaxHealth());
        s1.setChakra(s1.getMaxChakra());
        s2.setHealth(s2.getMaxHealth());
        s2.setChakra(s2.getMaxChakra());
    }

    private void sendBoth(String message) {
        p1.getOut().println(message);
        p2.getOut().println(message);
    }

    private void sendTo(ClientHandler target, String message) {
        target.getOut().println(message);
    }

    @Override
    public void run() {
        try {
            executeBattle();
        } catch (Exception e) {
            System.err.println("Multiplayer Battle Error: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    private void executeBattle() throws IOException {
        sendBoth("\n🔥=========================================================🔥");
        sendBoth("             💥 THE MULTIPLAYER DUEL BEGINS 💥               ");
        sendBoth("    " + s1.getName() + " (" + s1.getRank() + ") VS " + s2.getName() + " (" + s2.getRank() + ")");
        sendBoth("🔥=========================================================🔥\n");
        sleep(1000);

        int round = 1;
        while (s1.getHealth() > 0 && s2.getHealth() > 0) {
            sendBoth("\n-----------------------------------------------------------");
            sendBoth(" 🔴 ROUND " + round + " 🔴");
            sendBoth("-----------------------------------------------------------");
            
            displayShinobiStatus(s1);
            displayShinobiStatus(s2);
            sendBoth("");

            ClientHandler first, second;
            Shinobi fs, ss;
            
            if (s1.getSpeed() >= s2.getSpeed()) {
                first = p1; fs = s1;
                second = p2; ss = s2;
            } else {
                first = p2; fs = s2;
                second = p1; ss = s1;
            }

            // First Turn
            if (fs.getHealth() > 0) {
                if (fs.isStunned()) {
                    sendBoth("🌀 " + fs.getName() + " is stunned by Genjutsu and cannot move this turn!");
                    fs.decrementStun();
                } else {
                    boolean opponentEvading = (first == p1) ? s2Evading : s1Evading;
                    boolean actionUsed = executeTurn(first, second, opponentEvading);
                    
                    if (first == p1) s1Evading = false;
                    else s2Evading = false;

                    if (actionUsed && lastActionWasSubstitution) {
                        if (first == p1) s1Evading = true;
                        else s2Evading = true;
                    }
                }
                sleep(1500);
            }

            if (ss.getHealth() <= 0) break;

            // Second Turn
            if (ss.getHealth() > 0) {
                if (ss.isStunned()) {
                    sendBoth("🌀 " + ss.getName() + " is stunned by Genjutsu and cannot move this turn!");
                    ss.decrementStun();
                } else {
                    boolean opponentEvading = (second == p1) ? s2Evading : s1Evading;
                    boolean actionUsed = executeTurn(second, first, opponentEvading);
                    
                    if (second == p1) s1Evading = false;
                    else s2Evading = false;

                    if (actionUsed && lastActionWasSubstitution) {
                        if (second == p1) s1Evading = true;
                        else s2Evading = true;
                    }
                }
                sleep(1500);
            }

            round++;
        }

        Shinobi winner = (s1.getHealth() > 0) ? s1 : s2;
        Shinobi loser = (winner == s1) ? s2 : s1;

        sendBoth("\n🏆=========================================================🏆");
        sendBoth("               🎉 MULTIPLAYER DUEL FINISHED 🎉              ");
        sendBoth("        🥇 Winner: " + winner.getName() + " (" + String.format("%.1f", winner.getHealth()) + " HP left) 🥇");
        sendBoth("🏆=========================================================🏆\n");
        
        // Award rewards to winner (Ryo and EXP are local to client main game, but we display server rewards)
        sendBoth("🎁 Rewards for Victory: +80 Ryo & +100 EXP!");
    }

    private boolean executeTurn(ClientHandler attacker, ClientHandler defender, boolean opponentEvading) throws IOException {
        lastActionWasSubstitution = false;
        Shinobi attShinobi = attacker.getShinobi();
        Shinobi defShinobi = defender.getShinobi();

        while (true) {
            sendTo(attacker, "👉 It's your turn! Choose an action:");
            sendTo(attacker, "   [1] Basic Strike (Taijutsu - 0 CP, recovers 15 CP)");
            sendTo(attacker, "   [2] Use Jutsu (Uses CP or Stamina)");
            sendTo(attacker, "   [3] Chakra Charge (+40 CP)");
            sendTo(attacker, "   [4] Kawarimi / Substitution (15 CP, 55% dodge chance)");
            
            // Wait for input from attacker
            String input = readPlayerInput(attacker, "[INPUT] Selection (1-4):");
            if (input == null) throw new IOException("Player disconnected: " + attShinobi.getName());
            
            input = input.trim();
            if (input.equals("1")) {
                performBasicStrike(attacker, defender, opponentEvading);
                return true;
            } else if (input.equals("2")) {
                if (attShinobi.getKnownJutsus().isEmpty()) {
                    sendTo(attacker, "❌ You don't know any Jutsus! Choose another option.");
                    continue;
                }
                boolean casted = selectAndCastJutsu(attacker, defender, opponentEvading);
                if (casted) return true;
            } else if (input.equals("3")) {
                attShinobi.rechargeChakra(40);
                sendBoth("🌀 " + attShinobi.getName() + " focuses their energy and charges 40 CP!");
                return true;
            } else if (input.equals("4")) {
                if (attShinobi.getCurrentChakra() < 15) {
                    sendTo(attacker, "❌ Not enough chakra for Kawarimi (Needs 15 CP)!");
                    continue;
                }
                attShinobi.consumeChakra(15);
                sendBoth("🍃 " + attShinobi.getName() + " weaves signs and prepares a Substitution Jutsu!");
                lastActionWasSubstitution = true;
                return true;
            } else {
                sendTo(attacker, "❌ Invalid selection. Please enter a valid number (1-4).");
            }
        }
    }

    private boolean selectAndCastJutsu(ClientHandler attacker, ClientHandler defender, boolean opponentEvading) throws IOException {
        Shinobi attShinobi = attacker.getShinobi();
        Shinobi defShinobi = defender.getShinobi();

        while (true) {
            sendTo(attacker, "\n--- Select Jutsu (or enter 0 to go back) ---");
            List<Jutsu> list = attShinobi.getKnownJutsus();
            for (int i = 0; i < list.size(); i++) {
                Jutsu j = list.get(i);
                sendTo(attacker, "   [" + (i + 1) + "] " + j.getName() + " (" + j.getType() + " | Cost: " + j.getChakraCost() + " CP | Dmg: " + j.getDamage() + ")");
            }
            
            String input = readPlayerInput(attacker, "[INPUT] Selection (0-" + list.size() + "):");
            if (input == null) throw new IOException("Player disconnected: " + attShinobi.getName());
            
            input = input.trim();
            if (input.equals("0")) return false;

            try {
                int index = Integer.parseInt(input) - 1;
                if (index < 0 || index >= list.size()) {
                    sendTo(attacker, "❌ Invalid choice.");
                    continue;
                }
                
                Jutsu chosen = list.get(index);
                if (attShinobi.getCurrentChakra() < chosen.getChakraCost()) {
                    sendTo(attacker, "❌ Insufficient Chakra! " + chosen.getName() + " costs " + chosen.getChakraCost() + " CP, but you have " + attShinobi.getCurrentChakra() + " CP.");
                    continue;
                }

                if (opponentEvading && Math.random() < 0.55) {
                    attShinobi.consumeChakra(chosen.getChakraCost());
                    sendBoth("🌀 " + attShinobi.getName() + " uses " + chosen.getName() + "!");
                    sendBoth("💨 " + defShinobi.getName() + " uses Substitution Jutsu! *POOF* They leave behind a wooden log, dodging the attack completely!");
                } else {
                    sendBoth(chosen.use(attShinobi, defShinobi));
                }
                return true;

            } catch (NumberFormatException e) {
                sendTo(attacker, "❌ Please enter a valid number.");
            }
        }
    }

    private void performBasicStrike(ClientHandler attacker, ClientHandler defender, boolean opponentEvading) {
        Shinobi attShinobi = attacker.getShinobi();
        Shinobi defShinobi = defender.getShinobi();

        if (opponentEvading && Math.random() < 0.55) {
            attShinobi.rechargeChakra(15);
            sendBoth("⚔️ " + attShinobi.getName() + " lunges forward for a basic strike!");
            sendBoth("💨 " + defShinobi.getName() + " uses Substitution Jutsu! *POOF* They leave behind a wooden log, dodging the attack completely!");
        } else {
            double baseDmg = (8.0 + Math.random() * 5.0) * attShinobi.getAttackMultiplier();
            double finalDmg = baseDmg / defShinobi.getDefenseMultiplier();
            if (finalDmg < 0) finalDmg = 0;
            
            defShinobi.applyDamage(finalDmg);
            attShinobi.rechargeChakra(15);
            
            sendBoth("⚔️ " + attShinobi.getName() + " strikes " + defShinobi.getName() + " with a taijutsu blow, dealing " + 
                     String.format("%.1f", finalDmg) + " damage!");
            sendBoth("⚡ " + attShinobi.getName() + " recovers 15 CP (Chakra Pool)!");
        }
    }

    private String readPlayerInput(ClientHandler player, String prompt) throws IOException {
        player.getOut().println(prompt);
        return player.getIn().readLine();
    }

    private void displayShinobiStatus(Shinobi s) {
        int healthBars = (int) (s.getCurrentHealth() / s.getMaxHealth() * 15);
        if (healthBars < 0) healthBars = 0;
        StringBuilder hpStr = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            if (i < healthBars) hpStr.append("█");
            else hpStr.append("░");
        }

        int chakraBars = (int) ((double) s.getCurrentChakra() / s.getMaxChakra() * 10);
        if (chakraBars < 0) chakraBars = 0;
        StringBuilder cpStr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i < chakraBars) cpStr.append("█");
            else cpStr.append("░");
        }

        String stunTag = s.isStunned() ? " [STUNNED]" : "";
        String status = String.format("%-18s | HP: [%s] %5.1f/%5.1f | CP: [%s] %3d/%3d %s", 
            s.getName() + " (Lvl " + s.getLevel() + ")", 
            hpStr.toString(), s.getCurrentHealth(), s.getMaxHealth(), 
            cpStr.toString(), s.getCurrentChakra(), s.getMaxChakra(), 
            stunTag);
        
        sendBoth(status);
    }

    private void cleanup() {
        sendBoth("[DISCONNECT]");
        p1.closeConnection();
        p2.closeConnection();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
