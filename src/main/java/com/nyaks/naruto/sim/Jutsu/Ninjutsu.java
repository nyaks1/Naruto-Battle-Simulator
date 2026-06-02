package main.java.com.nyaks.naruto.sim.Jutsu;

import main.java.com.nyaks.naruto.sim.enums.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public class Ninjutsu extends Jutsu {

    public Ninjutsu(String name, ChakraAffinity affinity, int chakraCost, double damage, String description) {
        super(name, TypeOfJutsu.NINJUTSU, affinity, chakraCost, damage, description);
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
        double elementalMultiplier = getElementalMultiplier(this.getAffinity(), target.getChakraAffinity());
        double finalDamage = baseDamage * elementalMultiplier;
        
        // Target defense mitigation
        finalDamage = finalDamage / target.getDefenseMultiplier();
        if (finalDamage < 0) finalDamage = 0;

        target.applyDamage(finalDamage);

        String message = user.getName() + " performs Ninjutsu: " + this.getName() + "! " + this.getDescription() + "\n";
        message += "It deals " + String.format("%.1f", finalDamage) + " damage to " + target.getName() + "!";
        
        if (elementalMultiplier > 1.0) {
            message += " (Super Effective! Elemental Advantage!)";
        } else if (elementalMultiplier < 1.0) {
            message += " (Not very effective... Elemental Disadvantage)";
        }
        
        return message;
    }

    private double getElementalMultiplier(ChakraAffinity jutsuAffinity, ChakraAffinity targetAffinity) {
        if (jutsuAffinity == null || targetAffinity == null) {
            return 1.0;
        }

        // Fire > Wind > Lightning > Earth > Water > Fire
        switch (jutsuAffinity) {
            case FIRE:
                if (targetAffinity == ChakraAffinity.WIND) return 1.5;
                if (targetAffinity == ChakraAffinity.WATER) return 0.7;
                break;
            case WIND:
                if (targetAffinity == ChakraAffinity.LIGHTNING) return 1.5;
                if (targetAffinity == ChakraAffinity.FIRE) return 0.7;
                break;
            case LIGHTNING:
                if (targetAffinity == ChakraAffinity.EARTH) return 1.5;
                if (targetAffinity == ChakraAffinity.WIND) return 0.7;
                break;
            case EARTH:
                if (targetAffinity == ChakraAffinity.WATER) return 1.5;
                if (targetAffinity == ChakraAffinity.LIGHTNING) return 0.7;
                break;
            case WATER:
                if (targetAffinity == ChakraAffinity.FIRE) return 1.5;
                if (targetAffinity == ChakraAffinity.EARTH) return 0.7;
                break;
        }
        return 1.0;
    }
}
