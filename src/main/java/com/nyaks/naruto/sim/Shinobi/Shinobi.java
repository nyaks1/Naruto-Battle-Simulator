package main.java.com.nyaks.naruto.sim.Shinobi;

import java.util.List;
import java.util.ArrayList;
import main.java.com.nyaks.naruto.sim.Clan.Clan;
import main.java.com.nyaks.naruto.sim.Jutsu.Jutsu;
import main.java.com.nyaks.naruto.sim.enums.*;

public class Shinobi {

    private String name;
    private Village village;
    private Clan clan;
    private ChakraAffinity customAffinity;
    private ShinobiRank rank; 
    
    // Core HP/Chakra
    private double maxHealth;
    private double currentHealth;
    private int maxChakra;
    private int currentChakra;
    
    // Core attributes
    private int level;
    private int experience;
    private int ryo;
    
    private double attackPower;
    private double defensePower;
    private double speed;
    
    // Status effects
    private int stunDuration;
    
    // Known Jutsu
    private List<Jutsu> knownJutsus;

    public Shinobi(String name, Village village, int chakra, ShinobiRank rank, double health, Clan clan) {
        this.name = name;
        this.village = village;
        this.clan = clan;
        this.rank = rank;
        this.customAffinity = (clan != null) ? clan.getAffinity() : ChakraAffinity.WIND;
        
        this.level = 1;
        this.experience = 0;
        this.ryo = 100;
        
        this.attackPower = 10;
        this.defensePower = 10;
        this.speed = 10;
        
        // Scale health and chakra based on Clan multipliers
        double healthMultiplier = (clan != null) ? clan.getHealthMultiplier() : 1.0;
        double chakraMultiplier = (clan != null) ? clan.getChakraMultiplier() : 1.0;
        
        this.maxHealth = health * healthMultiplier;
        this.currentHealth = this.maxHealth;
        this.maxChakra = (int) (chakra * chakraMultiplier);
        this.currentChakra = this.maxChakra;
        
        this.stunDuration = 0;
        this.knownJutsus = new ArrayList<>();
    }

    public Shinobi(String name, Village village, int chakra, ShinobiRank rank, double health) {
        this(name, village, chakra, rank, health, null);
    }

    // Getters and Setters
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
        // Recalculate multipliers
        if (clan != null) {
            this.customAffinity = clan.getAffinity();
            double oldHealthRatio = this.currentHealth / this.maxHealth;
            double oldChakraRatio = (double) this.currentChakra / this.maxChakra;
            
            this.maxHealth = (this.maxHealth / clan.getHealthMultiplier()) * clan.getHealthMultiplier(); // simple re-cap
            this.currentHealth = this.maxHealth * oldHealthRatio;
            this.maxChakra = (int) ((this.maxChakra / clan.getChakraMultiplier()) * clan.getChakraMultiplier());
            this.currentChakra = (int) (this.maxChakra * oldChakraRatio);
        }
    }

    public ChakraAffinity getChakraAffinity() {
        return (clan != null) ? clan.getAffinity() : customAffinity;
    }

    public void setCustomAffinity(ChakraAffinity affinity) {
        this.customAffinity = affinity;
    }

    public int getChakra() {
        return currentChakra;
    }

    public void setChakra(int chakra) {
        this.currentChakra = Math.max(0, Math.min(chakra, maxChakra));
    }

    public ShinobiRank getRank() {
        return rank;
    }

    public void setRank(ShinobiRank rank) {
        this.rank = rank;
    }

    public double getHealth() {
        return currentHealth;
    }

    public void setHealth(double health) {
        this.currentHealth = Math.max(0.0, Math.min(health, maxHealth));
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public int getMaxChakra() {
        return maxChakra;
    }

    public int getCurrentChakra() {
        return currentChakra;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getRyo() {
        return ryo;
    }

    public void addRyo(int amount) {
        this.ryo += amount;
    }

    public void spendRyo(int amount) {
        this.ryo = Math.max(0, this.ryo - amount);
    }

    public double getAttackPower() {
        return attackPower;
    }

    public double getDefensePower() {
        return defensePower;
    }

    public double getSpeed() {
        return speed;
    }

    public List<Jutsu> getKnownJutsus() {
        return knownJutsus;
    }

    public void learnJutsu(Jutsu jutsu) {
        if (!knownJutsus.contains(jutsu)) {
            knownJutsus.add(jutsu);
        }
    }

    // Gameplay methods
    public void applyDamage(double amount) {
        this.currentHealth = Math.max(0.0, this.currentHealth - amount);
    }

    public void consumeChakra(int amount) {
        this.currentChakra = Math.max(0, this.currentChakra - amount);
    }

    public void rechargeChakra(int amount) {
        this.currentChakra = Math.min(this.maxChakra, this.currentChakra + amount);
    }

    public void heal(double amount) {
        this.currentHealth = Math.min(this.maxHealth, this.currentHealth + amount);
    }

    public void applyStun(int turns) {
        this.stunDuration = Math.max(this.stunDuration, turns);
    }

    public boolean isStunned() {
        return this.stunDuration > 0;
    }

    public void decrementStun() {
        if (this.stunDuration > 0) {
            this.stunDuration--;
        }
    }

    public int getStunDuration() {
        return stunDuration;
    }

    // Stat multipliers for calculations
    public double getAttackMultiplier() {
        double clanMultiplier = (clan != null) ? clan.getAttackMultiplier() : 1.0;
        return (attackPower / 10.0) * clanMultiplier;
    }

    public double getDefenseMultiplier() {
        double clanMultiplier = (clan != null) ? clan.getDefenseMultiplier() : 1.0;
        return (defensePower / 10.0) * clanMultiplier;
    }

    // Progression
    public String addExperience(int expGained) {
        this.experience += expGained;
        String log = name + " gained " + expGained + " EXP!";
        
        int expNeeded = getExpNeededForNextLevel();
        if (this.experience >= expNeeded) {
            log += "\n" + levelUp();
        }
        return log;
    }

    public int getExpNeededForNextLevel() {
        return level * 120;
    }

    private String levelUp() {
        this.experience -= getExpNeededForNextLevel();
        this.level++;
        
        // Increase stats
        double hpIncrease = 20 * ((clan != null) ? clan.getHealthMultiplier() : 1.0);
        int chakraIncrease = (int) (15 * ((clan != null) ? clan.getChakraMultiplier() : 1.0));
        
        this.maxHealth += hpIncrease;
        this.maxChakra += chakraIncrease;
        this.attackPower += 1.5;
        this.defensePower += 1.2;
        this.speed += 1.0;
        
        // Full restore
        this.currentHealth = this.maxHealth;
        this.currentChakra = this.maxChakra;
        
        String log = "⭐ LEVEL UP! " + name + " is now Level " + level + "! ⭐";
        log += "\n- Max HP increased to " + String.format("%.1f", maxHealth);
        log += "\n- Max Chakra increased to " + maxChakra;
        
        // Check for Rank Up
        String rankUpLog = updateRankBasedOnLevel();
        if (!rankUpLog.isEmpty()) {
            log += "\n" + rankUpLog;
        }
        
        return log;
    }

    private String updateRankBasedOnLevel() {
        ShinobiRank newRank = rank;
        if (level >= 50) {
            newRank = ShinobiRank.KAGE;
        } else if (level >= 30) {
            newRank = ShinobiRank.JONIN;
        } else if (level >= 15) {
            newRank = ShinobiRank.CHUNIN;
        } else if (level >= 5) {
            newRank = ShinobiRank.GENIN;
        }
        
        if (newRank != rank) {
            ShinobiRank oldRank = rank;
            this.rank = newRank;
            
            // Give rank-up bonuses
            this.attackPower += 3;
            this.defensePower += 3;
            this.speed += 2;
            
            return "🏆 RANK UP! " + name + " has been promoted from " + oldRank + " to " + newRank + "! 🏆";
        }
        return "";
    }
}