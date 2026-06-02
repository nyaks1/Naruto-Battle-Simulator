package main.java.com.nyaks.naruto.sim.Jutsu;

import main.java.com.nyaks.naruto.sim.enums.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public class Taijutsu extends Jutsu {
    private double healthCost; // Taijutsu might require sacrificing user's own health (stamina)

    public Taijutsu(String name, int chakraCost, double damage, double healthCost, String description) {
        super(name, TypeOfJutsu.TAIJUTSU, null, chakraCost, damage, description);
        this.healthCost = healthCost;
    }

    public double getHealthCost() {
        return healthCost;
    }

    @Override
    public String use(Shinobi user, Shinobi target) {
        if (healthCost > 0 && user.getCurrentHealth() <= healthCost) {
            return user.getName() + " tried to use " + this.getName() + " but did not have enough stamina (Health: " + 
                   String.format("%.1f", user.getCurrentHealth()) + "/" + String.format("%.1f", healthCost) + ")!";
        }

        // Consume health/chakra
        if (healthCost > 0) {
            user.applyDamage(healthCost); // Self damage
        }
        if (this.getChakraCost() > 0) {
            user.consumeChakra(this.getChakraCost());
        }

        double baseDamage = this.getDamage() * user.getAttackMultiplier();
        // Taijutsu ignores elemental matchups, but deals raw heavy damage
        double finalDamage = baseDamage / target.getDefenseMultiplier();
        if (finalDamage < 0) finalDamage = 0;

        target.applyDamage(finalDamage);

        String message = user.getName() + " unleashes Taijutsu: " + this.getName() + "! " + this.getDescription() + "\n";
        if (healthCost > 0) {
            message += user.getName() + " sacrifices " + String.format("%.1f", healthCost) + " HP for stamina! ";
        }
        message += "It deals " + String.format("%.1f", finalDamage) + " damage to " + target.getName() + "!";
        
        return message;
    }
}
