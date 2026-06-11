package main.java.com.nyaks.naruto.sim;

import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public final class BattleUtils {

    private BattleUtils() {}

    public static void displayShinobiStatus(Shinobi s) {
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

    public static void performBasicStrikeLocal(Shinobi attacker, Shinobi defender, boolean opponentEvading) {
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

    public static void performChakraChargeLocal(Shinobi attacker) {
        attacker.rechargeChakra(40);
        System.out.println("🌀 " + attacker.getName() + " focuses their energy and charges 40 CP!");
    }
}
