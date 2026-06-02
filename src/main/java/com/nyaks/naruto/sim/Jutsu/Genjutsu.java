package main.java.com.nyaks.naruto.sim.Jutsu;

import main.java.com.nyaks.naruto.sim.enums.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public class Genjutsu extends Jutsu {
    private double stunChance; // probability to stun the target (0.0 to 1.0)

    public Genjutsu(String name, int chakraCost, double damage, double stunChance, String description) {
        super(name, TypeOfJutsu.GENJUTSU, null, chakraCost, damage, description);
        this.stunChance = stunChance;
    }

    public double getStunChance() {
        return stunChance;
    }

    @Override
    public String use(Shinobi user, Shinobi target) {
        if (user.getCurrentChakra() < this.getChakraCost()) {
            return user.getName() + " tried to use " + this.getName() + " but did not have enough chakra (" + 
                   user.getCurrentChakra() + "/" + this.getChakraCost() + ")!";
        }

        // Consume chakra
        user.consumeChakra(this.getChakraCost());

        double baseDamage = this.getDamage() * user.getAttackMultiplier();
        double finalDamage = baseDamage / target.getDefenseMultiplier();
        if (finalDamage < 0) finalDamage = 0;

        target.applyDamage(finalDamage);

        String message = user.getName() + " casts Genjutsu: " + this.getName() + "! " + this.getDescription() + "\n";
        message += "It deals " + String.format("%.1f", finalDamage) + " damage to " + target.getName() + "!";

        // Apply stun chance
        if (Math.random() < stunChance) {
            target.applyStun(1);
            message += "\n" + target.getName() + " falls under the illusion and is STUNNED! (Will lose their next turn)";
        } else {
            message += "\n" + target.getName() + " managed to disrupt their chakra flow and break the illusion.";
        }
        
        return message;
    }
}
