package main.java.com.nyaks.naruto.sim.Shinobi;

import main.java.com.nyaks.naruto.sim.Clan.Clan;
import main.java.com.nyaks.naruto.sim.enums.*;
public class Shinobi {

    private String name;
    private Village village;
    private Clan clan;
    private int chakra;
    private ShinobiRank rank; 
    private double health;
    

    

    public Shinobi(String name, Village village, int chakra, ShinobiRank rank, double health, Clan clan) {
        this.name = name;
        this.village = village;
        this.chakra = chakra;
        this.clan = clan;
        this.health = health;  
        this.rank = rank; 
        rank = ShinobiRank.ACADEMY_STUDENT;

    }
    public Shinobi( String name, Village village, int chakra, ShinobiRank rank, double health) {
        this.name = name;
        this.village = village;
        this.chakra = chakra;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
    }

    public ShinobiRank getRank() {
        return rank;
    }

    public void setRank(ShinobiRank rank) {
        this.rank = rank;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}