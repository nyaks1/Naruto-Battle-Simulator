package main.java.com.nyaks.naruto.sim.Shinobi;

import java.util.List;
import java.util.ArrayList;
import main.java.com.nyaks.naruto.sim.Clan.Clan;
import main.java.com.nyaks.naruto.sim.Jutsu.Jutsu;
import main.java.com.nyaks.naruto.sim.Jutsu.Ninjutsu;
import main.java.com.nyaks.naruto.sim.Jutsu.Taijutsu;
import main.java.com.nyaks.naruto.sim.Jutsu.Genjutsu;
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
    if (this.clan == clan) return;
    
    double oldHealthRatio = this.maxHealth > 0 ? this.currentHealth / this.maxHealth : 1.0;
    double oldChakraRatio = this.maxChakra > 0 ? (double) this.currentChakra / this.maxChakra : 1.0;
    
    Clan oldClan = this.clan;
    this.clan = clan;
    
    if (clan != null) {
        this.customAffinity = clan.getAffinity();
        
        // Rebase maxHealth to level 1 value, then apply new multiplier
        double baseHealth = (oldClan != null) ? this.maxHealth / oldClan.getHealthMultiplier() : this.maxHealth;
        this.maxHealth = baseHealth * clan.getHealthMultiplier();
        this.currentHealth = this.maxHealth * oldHealthRatio;
        
        double baseChakra = (oldClan != null) ? this.maxChakra / oldClan.getChakraMultiplier() : this.maxChakra;
        this.maxChakra = (int) (baseChakra * clan.getChakraMultiplier());
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

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(";");
        sb.append(village.name()).append(";");
        sb.append(clan != null ? clan.getName() : "None").append(";");
        sb.append(level).append(";");
        sb.append(experience).append(";");
        sb.append(ryo).append(";");
        sb.append(attackPower).append(";");
        sb.append(defensePower).append(";");
        sb.append(speed).append(";");
        sb.append(maxHealth).append(";");
        sb.append(maxChakra).append(";");
        sb.append(rank.name()).append(";");
        
        sb.append(knownJutsus.size()).append(";");
        for (Jutsu j : knownJutsus) {
            sb.append(j.getName()).append("|");
            
            String typeName = "NINJUTSU";
            if (j instanceof Taijutsu) typeName = "TAIJUTSU";
            else if (j instanceof Genjutsu) typeName = "GENJUTSU";
            sb.append(typeName).append("|");
            
            sb.append(j.getAffinity() != null ? j.getAffinity().name() : "None").append("|");
            sb.append(j.getChakraCost()).append("|");
            sb.append(j.getDamage()).append("|");
            sb.append(j.getDescription().replace(";", ",")).append("|");
            
            if (j instanceof Taijutsu) {
                sb.append(((Taijutsu) j).getHealthCost());
            } else if (j instanceof Genjutsu) {
                sb.append(((Genjutsu) j).getStunChance());
            } else {
                sb.append("0.0");
            }
            sb.append(";");
        }
        return sb.toString();
    }

    public static Shinobi deserialize(String data) {
        String[] parts = data.split(";");
        String name = parts[0];
        Village village = Village.valueOf(parts[1]);
        String clanName = parts[2];
        
        Clan clan = null;
        if (!clanName.equalsIgnoreCase("None")) {
            if (clanName.equalsIgnoreCase("Uzumaki")) {
                clan = new Clan("Uzumaki", Village.KONOHAGAKURE, ChakraAffinity.WIND, "Adamantine Chains", "Giant Rasengan");
            } else if (clanName.equalsIgnoreCase("Uchiha")) {
                clan = new Clan("Uchiha", Village.KONOHAGAKURE, ChakraAffinity.FIRE, "Sharingan", "Fireball Jutsu");
            } else if (clanName.equalsIgnoreCase("Senju")) {
                clan = new Clan("Senju", Village.KONOHAGAKURE, ChakraAffinity.EARTH, "Wood Release", "Wood Style: Deep Forest Emergence");
            } else if (clanName.equalsIgnoreCase("Hyuga")) {
                clan = new Clan("Hyuga", Village.KONOHAGAKURE, ChakraAffinity.WIND, "Byakugan", "Eight Trigrams Sixty-Four Palms");
            } else if (clanName.equalsIgnoreCase("Kazekage")) {
                clan = new Clan("Kazekage", Village.SUNAGAKURE, ChakraAffinity.WIND, "Magnet Release", "Sand Coffin");
            } else {
                clan = new Clan(clanName, village, ChakraAffinity.WIND, "None");
            }
        }
        
        int level = Integer.parseInt(parts[3]);
        int exp = Integer.parseInt(parts[4]);
        int ryo = Integer.parseInt(parts[5]);
        double attack = Double.parseDouble(parts[6]);
        double defense = Double.parseDouble(parts[7]);
        double speed = Double.parseDouble(parts[8]);
        double maxHP = Double.parseDouble(parts[9]);
        int maxCP = Integer.parseInt(parts[10]);
        ShinobiRank rank = ShinobiRank.valueOf(parts[11]);
        
        Shinobi s = new Shinobi(name, village, maxCP, rank, maxHP, clan);
        s.level = level;
        s.experience = exp;
        s.ryo = ryo;
        s.attackPower = attack;
        s.defensePower = defense;
        s.speed = speed;
        s.currentHealth = maxHP;
        s.currentChakra = maxCP;
        
        int jutsuCount = Integer.parseInt(parts[12]);
        for (int i = 0; i < jutsuCount; i++) {
            String[] jParts = parts[13 + i].split("\\|");
            String jName = jParts[0];
            String type = jParts[1];
            String affStr = jParts[2];
            ChakraAffinity affinity = affStr.equalsIgnoreCase("None") ? null : ChakraAffinity.valueOf(affStr);
            int cost = Integer.parseInt(jParts[3]);
            double dmg = Double.parseDouble(jParts[4]);
            String desc = jParts[5];
            double extra = Double.parseDouble(jParts[6]);
            
            Jutsu j = null;
            if (type.equalsIgnoreCase("NINJUTSU")) {
                j = new Ninjutsu(jName, affinity, cost, dmg, desc);
            } else if (type.equalsIgnoreCase("TAIJUTSU")) {
                j = new Taijutsu(jName, cost, dmg, extra, desc);
            } else if (type.equalsIgnoreCase("GENJUTSU")) {
                j = new Genjutsu(jName, cost, dmg, extra, desc);
            }
            
            if (j != null) {
                s.learnJutsu(j);
            }
        }
        return s;
    }
}