package main.java.com.nyaks.naruto.sim.Jutsu;

public abstract class Jutsu {
    private String name;
    private TypeOfJutsu type;
    private ChakraAffinity affinity;
    private int chakraCost;
    private double damage;

    public enum TypeOfJutsu {
        NINJUTSU, TAIJUTSU, GENJUTSU, YANG, YIN, YIN_YANG
    }
    public enum ChakraAffinity {
        FIRE, WIND, WATER, LIGHTNING, EARTH
    }

}
