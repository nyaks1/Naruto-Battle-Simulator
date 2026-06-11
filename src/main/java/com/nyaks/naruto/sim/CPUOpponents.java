package main.java.com.nyaks.naruto.sim;

import main.java.com.nyaks.naruto.sim.Clan.Clan;
import main.java.com.nyaks.naruto.sim.Jutsu.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;
import main.java.com.nyaks.naruto.sim.enums.*;

public final class CPUOpponents {

    private static final Clan uchiha = new Clan("Uchiha", Village.KONOHAGAKURE, ChakraAffinity.FIRE, "Sharingan", "Fireball Jutsu");
    private static final Clan kazekage = new Clan("Kazekage", Village.SUNAGAKURE, ChakraAffinity.WIND, "Magnet Release", "Sand Coffin");
    private static final Clan hyuga = new Clan("Hyuga", Village.KONOHAGAKURE, ChakraAffinity.WIND, "Byakugan", "Eight Trigrams Sixty-Four Palms");
    private static final Clan nara = new Clan("Nara", Village.KONOHAGAKURE, ChakraAffinity.EARTH, "Shadow Possession");

    private CPUOpponents() {}

    public static Shinobi konohamaru() {
        Shinobi cpu = new Shinobi("Konohamaru", Village.KONOHAGAKURE, 50, ShinobiRank.ACADEMY_STUDENT, 75.0);
        cpu.setCustomAffinity(ChakraAffinity.WIND);
        cpu.learnJutsu(new Taijutsu("Shadow Clone Jutsu", 15, 30.0, 0.0, "Naruto/Konohamaru clones strike in kinetic cohesion."));
        cpu.learnJutsu(new Ninjutsu("Rasengan", ChakraAffinity.WIND, 50, 75.0, "A spinning sphere of concentrated chakra in the palm."));
        cpu.learnJutsu(new Ninjutsu("Burning Ash", ChakraAffinity.FIRE, 30, 42.0, "Spits gunpowder ash and ignites it."));
        return cpu;
    }

    public static Shinobi kiba() {
        Shinobi cpu = new Shinobi("Kiba Inuzuka", Village.KONOHAGAKURE, 60, ShinobiRank.GENIN, 90.0);
        cpu.setCustomAffinity(ChakraAffinity.EARTH);
        cpu.learnJutsu(new Taijutsu("Fang Over Fang", 10, 40.0, 5.0, "Kiba and Akamaru spin like drills to pierce the opponent."));
        cpu.learnJutsu(new Taijutsu("Two-Headed Wolf", 30, 70.0, 20.0, "Transforms into a giant multi-headed beast, crushing the enemy."));
        cpu.learnJutsu(new Taijutsu("Dynamic Entry", 0, 20.0, 0.0, "Aerial physical kick."));
        return cpu;
    }

    public static Shinobi shikamaru() {
        Shinobi cpu = new Shinobi("Shikamaru Nara", Village.KONOHAGAKURE, 90, ShinobiRank.CHUNIN, 110.0, nara);
        cpu.learnJutsu(new Genjutsu("Shadow Possession", 25, 10.0, 0.70, "Shikamaru binds the target's shadow, freezing them in place."));
        cpu.learnJutsu(new Genjutsu("Shadow Strangle", 40, 35.0, 0.70, "Strangles the target with physical shadow bindings."));
        levelUpCpuTo(cpu, 14);
        return cpu;
    }

    public static Shinobi asuma() {
        Shinobi cpu = new Shinobi("Asuma Sarutobi", Village.KONOHAGAKURE, 110, ShinobiRank.JONIN, 130.0);
        cpu.setCustomAffinity(ChakraAffinity.WIND);
        cpu.learnJutsu(new Taijutsu("Flying Swallow", 15, 45.0, 0.0, "Asuma infuses wind chakra into trench knives, extending blade length."));
        cpu.learnJutsu(new Ninjutsu("Burning Ash", ChakraAffinity.FIRE, 30, 42.0, "Spits gunpowder ash and ignites it."));
        cpu.learnJutsu(new Taijutsu("Dynamic Entry", 0, 20.0, 0.0, "Basic combat kick."));
        levelUpCpuTo(cpu, 28);
        return cpu;
    }

    public static Shinobi orochimaru() {
        Shinobi cpu = new Shinobi("Orochimaru", Village.KONOHAGAKURE, 180, ShinobiRank.KAGE, 180.0);
        cpu.setCustomAffinity(ChakraAffinity.WIND);
        cpu.learnJutsu(new Ninjutsu("Snake Hands", ChakraAffinity.EARTH, 25, 35.0, "Orochimaru summons multiple snakes from his sleeves to constrict target."));
        cpu.learnJutsu(new Ninjutsu("Edo Tensei", ChakraAffinity.EARTH, 80, 115.0, "Reanimates legendary warriors to strike down the opponent."));
        cpu.learnJutsu(new Genjutsu("Temple of Nirvana", 40, 22.0, 0.75, "Causes a rain of white feathers, putting the target to sleep (stun)."));
        levelUpCpuTo(cpu, 45);
        return cpu;
    }

    public static Shinobi madara() {
        Shinobi cpu = new Shinobi("Madara Uchiha", Village.KONOHAGAKURE, 250, ShinobiRank.KAGE, 250.0, uchiha);
        cpu.learnJutsu(new Ninjutsu("Majestic Destroyer Flame", ChakraAffinity.FIRE, 50, 80.0, "Uchiha style total-war flame wall that incinerates target."));
        cpu.learnJutsu(new Ninjutsu("Tengai Shinsei", ChakraAffinity.EARTH, 95, 140.0, "Madara drops a giant meteor from the atmosphere onto the arena."));
        cpu.learnJutsu(new Taijutsu("Susanoo Strike", 15, 55.0, 0.0, "Madara strikes with Susanoo arms."));
        levelUpCpuTo(cpu, 70);
        return cpu;
    }

    public static Shinobi temari() {
        Shinobi cpu = new Shinobi("Temari", Village.SUNAGAKURE, 80, ShinobiRank.GENIN, 100.0);
        cpu.setCustomAffinity(ChakraAffinity.WIND);
        cpu.learnJutsu(new Ninjutsu("Wind Scythe Jutsu", ChakraAffinity.WIND, 25, 38.0, "Zoning winds."));
        cpu.learnJutsu(new Ninjutsu("Quick Beheading Dance", ChakraAffinity.WIND, 60, 85.0, "Summons Kamatari."));
        levelUpCpuTo(cpu, 15);
        return cpu;
    }

    public static Shinobi neji() {
        Shinobi cpu = new Shinobi("Neji Hyuga", Village.KONOHAGAKURE, 110, ShinobiRank.CHUNIN, 130.0, hyuga);
        cpu.learnJutsu(new Taijutsu("Eight Trigrams 64 Palms", 15, 75.0, 0.0, "Gentle fist lock."));
        cpu.learnJutsu(new Taijutsu("Revolving Heaven", 30, 45.0, 0.0, "Absolute rotation."));
        levelUpCpuTo(cpu, 30);
        return cpu;
    }

    public static Shinobi gaaraFinal() {
        Shinobi cpu = new Shinobi("Gaara of the Sand", Village.SUNAGAKURE, 160, ShinobiRank.JONIN, 170.0, kazekage);
        cpu.learnJutsu(new Ninjutsu("Sand Coffin & Burial", ChakraAffinity.EARTH, 45, 65.0, "Sand bindings."));
        cpu.learnJutsu(new Ninjutsu("Sand Tsunami", ChakraAffinity.EARTH, 75, 95.0, "Tidal wave sand."));
        levelUpCpuTo(cpu, 45);
        return cpu;
    }

    public static void levelUpCpuTo(Shinobi cpu, int targetLevel) {
        for (int l = 1; l < targetLevel; l++) {
            cpu.addExperience(cpu.getExpNeededForNextLevel());
        }
        cpu.setHealth(cpu.getMaxHealth());
        cpu.setChakra(cpu.getMaxChakra());
    }
}
