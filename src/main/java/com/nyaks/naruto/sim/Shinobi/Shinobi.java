package main.java.com.nyaks.naruto.sim.Shinobi;

public class Shinobi {

    private String name;
    private String village;
    private String clan;
    private String chakra;
    private ShinobiRank rank; 
    private double health;
    
    public enum ShinobiRank {
        ACADEMY_STUDENT, GENIN, CHUNIN, TOKUBETSU_JONIN, JONIN,KAGE
    }

    public Shinobi(String name, String village, String chakra, ShinobiRank rank, double health) {
        
    }
}