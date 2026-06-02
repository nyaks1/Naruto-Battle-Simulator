package main.java.com.nyaks.naruto.sim;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import main.java.com.nyaks.naruto.sim.Jutsu.Jutsu;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public class Battle {
    private Shinobi p1;
    private Shinobi p2;
    private boolean p2IsCpu;
    private Scanner scanner;

    public Battle(Shinobi p1, Shinobi p2, boolean p2IsCpu, Scanner scanner) {
        this.p1 = p1;
        this.p2 = p2;
        this.p2IsCpu = p2IsCpu;
        this.scanner = scanner;
        
        // Reset health/chakra for the duel
        p1.setHealth(p1.getMaxHealth());
        p1.setChakra(p1.getMaxChakra());
        p2.setHealth(p2.getMaxHealth());
        p2.setChakra(p2.getMaxChakra());
    }

    public Shinobi start() {
        System.out.println("\n🔥=========================================================🔥");
        System.out.println("                   💥 THE DUEL BEGINS 💥                     ");
        System.out.println("   " + p1.getName() + " (" + p1.getRank() + ") VS " + p2.getName() + " (" + p2.getRank() + ")");
        System.out.println("🔥=========================================================🔥\n");
        sleep(1000);

        int round = 1;
        boolean p1Evading = false;
        boolean p2Evading = false;

        while (p1.getHealth() > 0 && p2.getHealth() > 0) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println(" 🔴 ROUND " + round + " 🔴");
            System.out.println("-----------------------------------------------------------");
            
            displayShinobiStatus(p1);
            displayShinobiStatus(p2);
            System.out.println();

            // Determine turn order based on Speed
            Shinobi first, second;
            boolean firstIsCpu, secondIsCpu;
            
            if (p1.getSpeed() >= p2.getSpeed()) {
                first = p1;
                firstIsCpu = false;
                second = p2;
                secondIsCpu = p2IsCpu;
            } else {
                first = p2;
                firstIsCpu = p2IsCpu;
                second = p1;
                secondIsCpu = false;
            }

            // --- First Character Turn ---
            if (first.getHealth() > 0) {
                if (first.isStunned()) {
                    System.out.println("🌀 " + first.getName() + " is stunned by Genjutsu and cannot move this turn!");
                    first.decrementStun();
                } else {
                    boolean opponentEvading = (first == p1) ? p2Evading : p1Evading;
                    boolean actionUsed = executeTurn(first, second, firstIsCpu, opponentEvading);
                    
                    // If substitution was active and they successfully hit or missed, reset evasion
                    if (first == p1) {
                        p1Evading = false; // Reset self-evasion when turn ends
                    } else {
                        p2Evading = false;
                    }
                    
                    // Check if they activated substitution during their turn
                    if (actionUsed && lastActionWasSubstitution) {
                        if (first == p1) p1Evading = true;
                        else p2Evading = true;
                    }
                }
                sleep(1200);
            }

            if (second.getHealth() <= 0) break;

            // --- Second Character Turn ---
            if (second.getHealth() > 0) {
                if (second.isStunned()) {
                    System.out.println("🌀 " + second.getName() + " is stunned by Genjutsu and cannot move this turn!");
                    second.decrementStun();
                } else {
                    boolean opponentEvading = (second == p1) ? p2Evading : p1Evading;
                    boolean actionUsed = executeTurn(second, first, secondIsCpu, opponentEvading);
                    
                    if (second == p1) {
                        p1Evading = false;
                    } else {
                        p2Evading = false;
                    }
                    
                    if (actionUsed && lastActionWasSubstitution) {
                        if (second == p1) p1Evading = true;
                        else p2Evading = true;
                    }
                }
                sleep(1200);
            }

            round++;
        }

        Shinobi winner = (p1.getHealth() > 0) ? p1 : p2;
        Shinobi loser = (winner == p1) ? p2 : p1;

        System.out.println("\n🏆=========================================================🏆");
        System.out.println("                   🎉 BATTLE FINISHED 🎉                    ");
        System.out.println("      🥇 Winner: " + winner.getName() + " (" + winner.getHealth() + " HP left) 🥇");
        System.out.println("🏆=========================================================🏆\n");

        if (winner == p1 && p2IsCpu) {
            // Player won against CPU - give rewards
            int expReward = 30 + (p2.getLevel() * 15);
            int ryoReward = 20 + (p2.getLevel() * 10);
            System.out.println("🎁 rewards for victory:");
            System.out.println("   + " + ryoReward + " Ryo");
            winner.addRyo(ryoReward);
            System.out.println("   " + winner.addExperience(expReward));
        } else if (p2IsCpu) {
            // Player lost against CPU - give partial reward
            int expReward = 10;
            System.out.println("🎖 Consolation rewards:");
            System.out.println("   " + p1.addExperience(expReward));
        }

        return winner;
    }

    private boolean lastActionWasSubstitution = false;

    private boolean executeTurn(Shinobi attacker, Shinobi defender, boolean isCpu, boolean opponentEvading) {
        lastActionWasSubstitution = false;
        if (isCpu) {
            return executeCpuTurn(attacker, defender, opponentEvading);
        } else {
            return executePlayerTurn(attacker, defender, opponentEvading);
        }
    }

    private boolean executePlayerTurn(Shinobi attacker, Shinobi defender, boolean opponentEvading) {
        while (true) {
            System.out.println("👉 " + attacker.getName() + "'s Turn! Choose an action:");
            System.out.println("   [1] Basic Strike (Taijutsu - 0 CP, recovers 15 CP)");
            System.out.println("   [2] Use Jutsu (Uses CP or Stamina)");
            System.out.println("   [3] Chakra Charge (+40 CP)");
            System.out.println("   [4] Kawarimi / Substitution (15 CP, 55% dodge chance)");
            
            System.out.print("Input: ");
            String inputStr = scanner.nextLine().trim();
            
            if (inputStr.equals("1")) {
                // Basic strike
                performBasicStrike(attacker, defender, opponentEvading);
                return true;
            } else if (inputStr.equals("2")) {
                // Select and use jutsu
                if (attacker.getKnownJutsus().isEmpty()) {
                    System.out.println("❌ You don't know any Jutsus! Choose another option.");
                    continue;
                }
                
                boolean jutsuCast = selectAndCastJutsu(attacker, defender, opponentEvading);
                if (jutsuCast) {
                    return true;
                }
            } else if (inputStr.equals("3")) {
                performChakraCharge(attacker);
                return true;
            } else if (inputStr.equals("4")) {
                if (attacker.getCurrentChakra() < 15) {
                    System.out.println("❌ Not enough chakra for Kawarimi (Needs 15 CP)!");
                    continue;
                }
                attacker.consumeChakra(15);
                System.out.println("🍃 " + attacker.getName() + " weaves signs and prepares a Substitution Jutsu!");
                lastActionWasSubstitution = true;
                return true;
            } else {
                System.out.println("❌ Invalid selection. Please enter a valid number (1-4).");
            }
        }
    }

    private boolean executeCpuTurn(Shinobi attacker, Shinobi defender, boolean opponentEvading) {
        System.out.println("🤖 " + attacker.getName() + " (CPU) is thinking...");
        sleep(1000);

        // CPU Decision Logic
        // 1. If CPU has very low chakra, charge it
        if (attacker.getCurrentChakra() < 15) {
            performChakraCharge(attacker);
            return true;
        }

        // 2. Decide whether to use Jutsu or Basic Attack
        List<Jutsu> playableJutsus = getAffordableJutsus(attacker);
        if (!playableJutsus.isEmpty() && Math.random() < 0.65) {
            // Select the highest damage jutsu or a random one
            Jutsu chosen = playableJutsus.get((int) (Math.random() * playableJutsus.size()));
            
            // Check for Substitution evasion
            if (opponentEvading && Math.random() < 0.55) {
                attacker.consumeChakra(chosen.getChakraCost()); // Still consume the chakra/health cost
                System.out.println("🌀 " + attacker.getName() + " uses " + chosen.getName() + "!");
                System.out.println("💨 " + defender.getName() + " uses Substitution Jutsu! *POOF* They leave behind a wooden log, dodging the attack completely!");
            } else {
                System.out.println(chosen.use(attacker, defender));
            }
            return true;
        }

        // 3. Occasionally use Kawarimi if health is low (< 40%)
        if (attacker.getCurrentHealth() < (attacker.getMaxHealth() * 0.4) && attacker.getCurrentChakra() >= 15 && Math.random() < 0.4) {
            attacker.consumeChakra(15);
            System.out.println("🍃 " + attacker.getName() + " prepares a Substitution Jutsu!");
            lastActionWasSubstitution = true;
            return true;
        }

        // 4. Default: Basic Strike
        performBasicStrike(attacker, defender, opponentEvading);
        return true;
    }

    private List<Jutsu> getAffordableJutsus(Shinobi attacker) {
        List<Jutsu> affordable = new ArrayList<>();
        for (Jutsu j : attacker.getKnownJutsus()) {
            if (attacker.getCurrentChakra() >= j.getChakraCost()) {
                affordable.add(j);
            }
        }
        return affordable;
    }

    private boolean selectAndCastJutsu(Shinobi attacker, Shinobi defender, boolean opponentEvading) {
        while (true) {
            System.out.println("\n--- Select Jutsu (or enter 0 to go back) ---");
            List<Jutsu> list = attacker.getKnownJutsus();
            for (int i = 0; i < list.size(); i++) {
                Jutsu j = list.get(i);
                System.out.println("   [" + (i + 1) + "] " + j.getName() + " (" + j.getType() + " | Cost: " + j.getChakraCost() + " CP | Dmg: " + j.getDamage() + ")");
            }
            
            System.out.print("Input: ");
            String inputStr = scanner.nextLine().trim();
            if (inputStr.equals("0")) {
                return false; // Back to main turn choice
            }

            try {
                int index = Integer.parseInt(inputStr) - 1;
                if (index < 0 || index >= list.size()) {
                    System.out.println("❌ Invalid choice. Enter a number within the list range.");
                    continue;
                }
                
                Jutsu chosen = list.get(index);
                if (attacker.getCurrentChakra() < chosen.getChakraCost()) {
                    System.out.println("❌ Insufficient Chakra! " + chosen.getName() + " costs " + chosen.getChakraCost() + " CP, but you have " + attacker.getCurrentChakra() + " CP.");
                    continue;
                }

                // Check for evasion
                if (opponentEvading && Math.random() < 0.55) {
                    attacker.consumeChakra(chosen.getChakraCost());
                    System.out.println("🌀 " + attacker.getName() + " uses " + chosen.getName() + "!");
                    System.out.println("💨 " + defender.getName() + " uses Substitution Jutsu! *POOF* They leave behind a wooden log, dodging the attack completely!");
                } else {
                    System.out.println(chosen.use(attacker, defender));
                }
                return true;

            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    private void performBasicStrike(Shinobi attacker, Shinobi defender, boolean opponentEvading) {
        if (opponentEvading && Math.random() < 0.55) {
            attacker.rechargeChakra(15);
            System.out.println("⚔️ " + attacker.getName() + " lunges forward for a basic strike!");
            System.out.println("💨 " + defender.getName() + " uses Substitution Jutsu! *POOF* They leave behind a wooden log, dodging the attack completely!");
        } else {
            double baseDmg = (8.0 + Math.random() * 5.0) * attacker.getAttackMultiplier();
            double finalDmg = baseDmg / defender.getDefenseMultiplier();
            if (finalDmg < 0) finalDmg = 0;
            
            defender.applyDamage(finalDmg);
            attacker.rechargeChakra(15);
            
            System.out.println("⚔️ " + attacker.getName() + " strikes " + defender.getName() + " with a taijutsu blow, dealing " + 
                               String.format("%.1f", finalDmg) + " damage!");
            System.out.println("⚡ " + attacker.getName() + " recovers 15 CP (Chakra Pool)!");
        }
    }

    private void performChakraCharge(Shinobi attacker) {
        attacker.rechargeChakra(40);
        System.out.println("🌀 " + attacker.getName() + " focuses their energy and charges 40 CP!");
    }

    private void displayShinobiStatus(Shinobi s) {
        // Render stylized bars
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
        System.out.printf("%-18s | HP: [%s] %5.1f/%5.1f | CP: [%s] %3d/%3d %s\n", 
            s.getName() + " (Lvl " + s.getLevel() + ")", 
            hpStr.toString(), s.getCurrentHealth(), s.getMaxHealth(), 
            cpStr.toString(), s.getCurrentChakra(), s.getMaxChakra(), 
            stunTag);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
