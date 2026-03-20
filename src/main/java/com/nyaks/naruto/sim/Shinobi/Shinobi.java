package main.java.com.nyaks.naruto.sim.Shinobi;

import main.java.com.nyaks.naruto.sim.Clan.Clan;

public class Shinobi {

    private String name;
    private NameOfVillage village;
    private Clan clan;
    private int chakra;
    private ShinobiRank rank; 
    private double health;
    
    public enum NameOfVillage {
        KONOHAGAKURE,KUMOGAKURE, IWAGAKURE, KIRIGAKURE, SUNAGAKURE
    }
    public enum ShinobiRank {
        ACADEMY_STUDENT, GENIN, CHUNIN, TOKUBETSU_JONIN, JONIN,KAGE
    }

    public Shinobi(String name, NameOfVillage village, int chakra, ShinobiRank rank, double health, Clan clan) {
        // if (clan == null) {}
        this.name = name;
        this.village = village;
        this.chakra = chakra;
        this.clan = clan;
        this.health = health;  
        this.rank = rank; 

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameOfVillage getVillage() {
        return village;
    }

    public void setVillage(NameOfVillage village) {
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