package main.java.com.nyaks.naruto.sim.Jutsu;
import main.java.com.nyaks.naruto.sim.enums.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public abstract class Jutsu {
    private String name;
    private TypeOfJutsu type;
    private ChakraAffinity affinity;
    private int chakraCost;
    private double damage;   
    private String description;
    
    public Jutsu(String name, TypeOfJutsu type, ChakraAffinity affinity, int chakraCost, double damage, String description) {
        this.name = name;
        this.type = type;
        this.affinity = affinity;
        this.chakraCost = chakraCost;
        this.damage = damage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public TypeOfJutsu getType() {
        return type;
    }

    public ChakraAffinity getAffinity() {
        return affinity;
    }

    public int getChakraCost() {
        return chakraCost;
    }

    public double getDamage() {
        return damage;
    }

    public String getDescription() {
        return description;
    }

    public abstract String use(Shinobi user, Shinobi target);
}
