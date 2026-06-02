package main.java.com.nyaks.naruto.sim.Clan;
import main.java.com.nyaks.naruto.sim.enums.*;
public class Clan {
  private String name;
  private Village village;
  private ChakraAffinity affinity;
  private String kekkeiGenkai;
  private String clanJutsu;

  public Clan(String name, Village village, ChakraAffinity affinity, String kekkeiGenkai, String clanJutsu) {
    this.name = name;
    this.village = village;
    this.affinity = affinity;
    this.kekkeiGenkai = kekkeiGenkai;
    this.clanJutsu = clanJutsu;
  }

  public Clan (String name, Village village, ChakraAffinity affinity, String clanJutsu) {
    this.name = name;
    this.village = village;
    this.affinity = affinity;
    this.kekkeiGenkai = "None";
    this.clanJutsu = clanJutsu;
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

  // Getters for multipliers based on clan characteristics
  public double getHealthMultiplier() {
    if (name.equalsIgnoreCase("Uzumaki") || name.equalsIgnoreCase("Senju")) {
      return 1.25; // 25% boost
    }
    return 1.0;
  }

  public double getChakraMultiplier() {
    if (name.equalsIgnoreCase("Uzumaki") || name.equalsIgnoreCase("Nara")) {
      return 1.20; // 20% boost
    }
    return 1.0;
  }

  public double getAttackMultiplier() {
    if (name.equalsIgnoreCase("Uchiha") || name.equalsIgnoreCase("Senju")) {
      return 1.15; // 15% boost
    }
    return 1.0;
  }

  public double getDefenseMultiplier() {
    if (name.equalsIgnoreCase("Hyuga") || name.equalsIgnoreCase("Kazekage")) {
      return 1.20; // 20% boost
    }
    return 1.0;
  }
}
