package main.java.com.nyaks.naruto.sim.Jutsu;
import main.java.com.nyaks.naruto.sim.enums.*;
public abstract class Jutsu {
    private String name;
    private TypeOfJutsu type;
    private ChakraAffinity affinity;
    private int chakraCost;
    private double damage;   
    
    public Jutsu (String name, TypeOfJutsu type, ChakraAffinity affinity, int chakraCost, double damage) {
        this.name = name;
        this.type = type;
        this.affinity = affinity;
        this.chakraCost = chakraCost;
        this.damage = damage;
    }
}
