package main.java.com.nyaks.naruto.sim.Clan;
import main.java.com.nyaks.naruto.sim.enums.*;
public class Clan {
  private String name;
  private Village village;
  private ChakraAffinity affinity;
  private String kekkeiGenkai;
  private String clanJutsu;
  private double healthMultiplier;
  private double chakraMultiplier;
  private double attackMultiplier;
  private double defenseMultiplier;

  public Clan(String name, Village village, ChakraAffinity affinity, String kekkeiGenkai, String clanJutsu) {
    this(name, village, affinity, clanJutsu);
    this.kekkeiGenkai = kekkeiGenkai;
  }

  public Clan(String name, Village village, ChakraAffinity affinity, String clanJutsu) {
    this.name = name;
    this.village = village;
    this.affinity = affinity;
    this.kekkeiGenkai = "None";
    this.clanJutsu = clanJutsu;
    this.healthMultiplier = resolveHealthMultiplier(name);
    this.chakraMultiplier = resolveChakraMultiplier(name);
    this.attackMultiplier = resolveAttackMultiplier(name);
    this.defenseMultiplier = resolveDefenseMultiplier(name);
  }

  private static double resolveHealthMultiplier(String clanName) {
    return switch (clanName.toLowerCase()) {
      case "uzumaki", "senju" -> 1.25;
      default -> 1.0;
    };
  }

  private static double resolveChakraMultiplier(String clanName) {
    return switch (clanName.toLowerCase()) {
      case "uzumaki", "nara" -> 1.20;
      default -> 1.0;
    };
  }

  private static double resolveAttackMultiplier(String clanName) {
    return switch (clanName.toLowerCase()) {
      case "uchiha", "senju" -> 1.15;
      default -> 1.0;
    };
  }

  private static double resolveDefenseMultiplier(String clanName) {
    return switch (clanName.toLowerCase()) {
      case "hyuga", "kazekage" -> 1.20;
      default -> 1.0;
    };
  }

  public String getName() {
    return name;
  }

  public Village getVillage() {
    return village;
  }

  public ChakraAffinity getAffinity() {
    return affinity;
  }

  public String getKekkeiGenkai() {
    return kekkeiGenkai;
  }

  public String getClanJutsu() {
    return clanJutsu;
  }

  public double getHealthMultiplier() {
    return healthMultiplier;
  }

  public double getChakraMultiplier() {
    return chakraMultiplier;
  }

  public double getAttackMultiplier() {
    return attackMultiplier;
  }

  public double getDefenseMultiplier() {
    return defenseMultiplier;
  }
}
