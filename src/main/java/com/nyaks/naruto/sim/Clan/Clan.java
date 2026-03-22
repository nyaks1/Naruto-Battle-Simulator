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
    this.clanJutsu = clanJutsu;
  }

}
