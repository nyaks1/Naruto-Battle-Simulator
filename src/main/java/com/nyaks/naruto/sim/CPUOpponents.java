package main.java.com.nyaks.naruto.sim;

import main.java.com.nyaks.naruto.sim.Clan.Clan;
import main.java.com.nyaks.naruto.sim.Jutsu.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;
import main.java.com.nyaks.naruto.sim.enums.*;

public final class CPUOpponents {

    private static final Clan uchiha = new Clan("Uchiha", Village.KONOHAGAKURE, ChakraAffinity.FIRE, "Sharingan", "Fireball Jutsu");

    private CPUOpponents() {}

    // --- Single Battle Opponents (6 difficulties) ---

    public static Shinobi hidan() {
        Shinobi cpu = new Shinobi("Hidan", Village.KUMOGAKURE, 60, ShinobiRank.GENIN, 100.0);
        cpu.setCustomAffinity(ChakraAffinity.WATER);
        cpu.learnJutsu(new Ninjutsu("Jashin Ritual", ChakraAffinity.WATER, 30, 45.0, "Hidan's cursed ritual links damage to the target through blood sacrifice."));
        cpu.learnJutsu(new Taijutsu("Triple Threat", 0, 38.0, 0.0, "Hidan swings his triple-bladed scythe in a wide arc."));
        cpu.learnJutsu(new Taijutsu("Scythe Sweep", 10, 50.0, 10.0, "A heavy scythe swing that costs stamina but tears through defenses."));
        levelUpCpuTo(cpu, 2);
        return cpu;
    }

    public static Shinobi konan() {
        Shinobi cpu = new Shinobi("Konan", Village.KUMOGAKURE, 80, ShinobiRank.GENIN, 95.0);
        cpu.setCustomAffinity(ChakraAffinity.WIND);
        cpu.learnJutsu(new Ninjutsu("Dance of the Shikigami", ChakraAffinity.WIND, 25, 35.0, "Konan unfurls thousands of paper sheets to slash the target."));
        cpu.learnJutsu(new Ninjutsu("Paper Chasm", ChakraAffinity.WIND, 40, 55.0, "Erupts a torrent of paper from below to engulf the opponent."));
        cpu.learnJutsu(new Ninjutsu("Paper Clone", ChakraAffinity.WIND, 15, 20.0, "Creates a paper duplicate to confuse the enemy."));
        levelUpCpuTo(cpu, 6);
        return cpu;
    }

    public static Shinobi deidara() {
        Shinobi cpu = new Shinobi("Deidara", Village.KUMOGAKURE, 100, ShinobiRank.CHUNIN, 115.0);
        cpu.setCustomAffinity(ChakraAffinity.EARTH);
        cpu.learnJutsu(new Ninjutsu("C1 Clay Bomb", ChakraAffinity.EARTH, 20, 30.0, "Throws a small clay bird that explodes on contact."));
        cpu.learnJutsu(new Ninjutsu("C3 Macro", ChakraAffinity.EARTH, 50, 70.0, "Drops a massive clay bombing run from the sky."));
        cpu.learnJutsu(new Ninjutsu("C4 Garuda", ChakraAffinity.EARTH, 75, 100.0, "Inhales and exhales a giant clay doll filled with microscopic bombs that destroy on a cellular level."));
        cpu.learnJutsu(new Taijutsu("Clay Dragon", 15, 45.0, 0.0, "Rides an explosive clay dragon into a diving attack."));
        levelUpCpuTo(cpu, 14);
        return cpu;
    }

    public static Shinobi sasori() {
        Shinobi cpu = new Shinobi("Sasori", Village.KUMOGAKURE, 120, ShinobiRank.JONIN, 140.0);
        cpu.setCustomAffinity(ChakraAffinity.FIRE);
        cpu.learnJutsu(new Ninjutsu("Iron Sand World Order", ChakraAffinity.EARTH, 45, 65.0, "Controls a massive cloud of iron sand to crush the opponent."));
        cpu.learnJutsu(new Ninjutsu("Puppet Jutsu: Hundred puppets", ChakraAffinity.FIRE, 60, 80.0, "Unleashes an army of puppets to swarm the target simultaneously."));
        cpu.learnJutsu(new Ninjutsu("Flame Breath", ChakraAffinity.FIRE, 30, 42.0, "Hiruko's puppet unleashes a stream of fire."));
        cpu.learnJutsu(new Taijutsu("Hiruko Strike", 10, 35.0, 0.0, "Attacks from within the Hiruko puppet shell."));
        levelUpCpuTo(cpu, 28);
        return cpu;
    }

    public static Shinobi kisame() {
        Shinobi cpu = new Shinobi("Kisame Hoshigaki", Village.KUMOGAKURE, 160, ShinobiRank.JONIN, 170.0);
        cpu.setCustomAffinity(ChakraAffinity.WATER);
        cpu.learnJutsu(new Ninjutsu("Water Prison Shark Dance", ChakraAffinity.WATER, 40, 55.0, "Creates a dome of water and fights as a shark within it, draining the opponent's chakra."));
        cpu.learnJutsu(new Ninjutsu("Five Sharks Jutsu", ChakraAffinity.WATER, 35, 48.0, "Summons five water sharks to bite the target from all angles."));
        cpu.learnJutsu(new Ninjutsu("Water Dragon Bullet", ChakraAffinity.WATER, 45, 65.0, "Shapes a giant water dragon to crush the opponent."));
        cpu.learnJutsu(new Taijutsu("Samehada Absorb", 0, 40.0, 0.0, "Samehada latches onto the target, draining their chakra mid-combat."));
        levelUpCpuTo(cpu, 45);
        return cpu;
    }

    public static Shinobi pain() {
        Shinobi cpu = new Shinobi("Pain (Nagato)", Village.KUMOGAKURE, 220, ShinobiRank.KAGE, 220.0);
        cpu.setCustomAffinity(ChakraAffinity.EARTH);
        cpu.learnJutsu(new Ninjutsu("Shinra Tensei", ChakraAffinity.EARTH, 55, 90.0, "Releases a massive repulsive force that blasts everything away from Pain."));
        cpu.learnJutsu(new Ninjutsu("Chibaku Tensei", ChakraAffinity.EARTH, 90, 140.0, "Creates a massive gravitational sphere that pulls the target into a moon-like prison."));
        cpu.learnJutsu(new Genjutsu("Almighty Push", 35, 30.0, 0.50, "Pain's Deva Path exerts divine pressure, staggering and stunning the target."));
        cpu.learnJutsu(new Ninjutsu("Animal Path Summon", ChakraAffinity.EARTH, 40, 55.0, "Summons a massive multi-headed creature to maul the opponent."));
        levelUpCpuTo(cpu, 70);
        return cpu;
    }

    // --- Tournament Opponents (4 rounds, escalating) ---

    public static Shinobi tournamentHidan() {
        Shinobi cpu = hidan();
        levelUpCpuTo(cpu, 3);
        return cpu;
    }

    public static Shinobi tournamentDeidara() {
        Shinobi cpu = deidara();
        return cpu;
    }

    public static Shinobi tournamentKisame() {
        Shinobi cpu = kisame();
        return cpu;
    }

    public static Shinobi tournamentPain() {
        return pain();
    }

    // --- Shared Utility ---

    public static void levelUpCpuTo(Shinobi cpu, int targetLevel) {
        for (int l = 1; l < targetLevel; l++) {
            cpu.addExperience(cpu.getExpNeededForNextLevel());
        }
        cpu.setHealth(cpu.getMaxHealth());
        cpu.setChakra(cpu.getMaxChakra());
    }
}
