package main.java.com.nyaks.naruto.sim.Jutsu;

public abstract class Jutsu {
    private String name;
    private String type;
    private String affinity;
    private int chakraCost;
    private float damage;

    enum TypeOfJutsu {
        Ninjutsu,
        Taijutsu,
        Genjutsu
    }
    enum chakraAffinity {
        Fire,
        Wind,
        Water,
        Lightning
    }
}
