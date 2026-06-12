package main.java.com.nyaks.naruto.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.java.com.nyaks.naruto.sim.Clan.Clan;
import main.java.com.nyaks.naruto.sim.Jutsu.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;
import main.java.com.nyaks.naruto.sim.enums.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Shinobi playerShinobi = null;
    private static JutsuShop shop = new JutsuShop();

    // Reusable Clans
    private static Clan uzumaki = new Clan("Uzumaki", Village.KONOHAGAKURE, ChakraAffinity.WIND, "Adamantine Chains", "Giant Rasengan");
    private static Clan uchiha = new Clan("Uchiha", Village.KONOHAGAKURE, ChakraAffinity.FIRE, "Sharingan", "Fireball Jutsu");
    private static Clan senju = new Clan("Senju", Village.KONOHAGAKURE, ChakraAffinity.EARTH, "Wood Release", "Wood Style: Deep Forest Emergence");
    private static Clan hyuga = new Clan("Hyuga", Village.KONOHAGAKURE, ChakraAffinity.WIND, "Byakugan", "Eight Trigrams Sixty-Four Palms");
    private static Clan kazekage = new Clan("Kazekage", Village.SUNAGAKURE, ChakraAffinity.WIND, "Magnet Release", "Sand Coffin");

    public static void main(String[] args) {
        displayTitleScreen();
        characterSelectionScreen();

        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = getSafeStringInput("Input Choice: ");
            switch (choice) {
                case "1":
                    startSingleBattle();
                    break;
                case "2":
                    startTournamentMode();
                    break;
                case "3":
                    visitJutsuShop();
                    break;
                case "4":
                    displayShinobiProfile();
                    break;
                case "5":
                    startMultiplayerMode();
                    break;
                case "6":
                    characterSelectionScreen(); // Switch Shinobi
                    break;
                case "7":
                    System.out.println("\n👋 Thank you for playing Naruto Battle Simulator! Farewell, Shinobi! 🍃\n");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid option. Please select 1 to 7.");
            }
        }
    }

    private static void displayTitleScreen() {
        System.out.println("========================================================================");
        System.out.println("              🍥  NARUTO SHINOBI BATTLE SIMULATOR  🍥                  ");
        System.out.println("                - Learn OOP with the Will of Fire -                    ");
        System.out.println("========================================================================");
        System.out.println("                        _                 _   _                         ");
        System.out.println("                       | |               | | (_)                        ");
        System.out.println("  _ __   __ _ _ __ _  _| |_ ___    ___  _| |_ _ _ __ ___                ");
        System.out.println(" | '_ \\ / _` | '__| | | | __/ _ \\  / __|/ _` | | '_ ` _ \\               ");
        System.out.println(" | | | | (_| | |  | |_| | || (_) | \\__ \\ (_| | | | | | | |              ");
        System.out.println(" |_| |_|\\__,_|_|   \\__,_|\\__\\___/  |___/\\__,_|_|_| |_| |_|              ");
        System.out.println("                                                                        ");
        System.out.println("========================================================================");
    }

    private static void displayMainMenu() {
        System.out.println("\n=========================== MAIN MENU ===========================");
        System.out.println(" Currently Playing: " + playerShinobi.getName() + " (" + playerShinobi.getRank() + " - Lvl " + playerShinobi.getLevel() + ")");
        System.out.println(" Ryo Balance: " + playerShinobi.getRyo() + " Ryo | CP Affinity: " + playerShinobi.getChakraAffinity());
        System.out.println("-----------------------------------------------------------------");
        System.out.println(" [1] ⚔️ Single Battle (Fight a CPU Opponent)");
        System.out.println(" [2] 🏆 Tournament Mode (Climb the Shinobi Ranks!)");
        System.out.println(" [3] 🍃 Visit Konoha Jutsu Shop");
        System.out.println(" [4] 📜 View Shinobi Stats & Known Jutsus");
        System.out.println(" [5] 🌐 Multiplayer Online Mode");
        System.out.println(" [6] 👥 Switch Shinobi / Create Custom Character");
        System.out.println(" [7] 🚪 Exit Game");
        System.out.println("=================================================================");
    }

    private static void characterSelectionScreen() {
        System.out.println("\n👥 SELECT YOUR SHINOBI 👥");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(" [1] Naruto Uzumaki  - Uzumaki Clan | High HP & CP | Wind");
        System.out.println(" [2] Sasuke Uchiha   - Uchiha Clan  | High Offense | Fire");
        System.out.println(" [3] Kakashi Hatake  - Copy Ninja   | Versatile Jutsus | Lightning");
        System.out.println(" [4] Gaara           - Kazekage Clan| High Defense | Earth");
        System.out.println(" [5] Itachi Uchiha   - Genjutsu Master | High Stun Tactics | Fire");
        System.out.println(" [6] Rock Lee        - Taijutsu Specialist | Heavy Physical Damage | Wind");
        System.out.println(" [7] 🔨 Create Custom Shinobi");
        System.out.println("-----------------------------------------------------------------");
        
        while (true) {
            String choice = getSafeStringInput("Choose character (1-7): ");
            switch (choice) {
                case "1":
                    playerShinobi = createNaruto();
                    System.out.println("✨ Selected Naruto Uzumaki! Believe it! 🍥");
                    return;
                case "2":
                    playerShinobi = createSasuke();
                    System.out.println("✨ Selected Sasuke Uchiha! To restore my clan... 👁️");
                    return;
                case "3":
                    playerShinobi = createKakashi();
                    System.out.println("✨ Selected Kakashi Hatake! I will protect my comrades. 📖");
                    return;
                case "4":
                    playerShinobi = createGaara();
                    System.out.println("✨ Selected Gaara! Protecting the village from the shadows... ⏳");
                    return;
                case "5":
                    playerShinobi = createItachi();
                    System.out.println("✨ Selected Itachi Uchiha! Foolish little brother... 👁️‍🗨️");
                    return;
                case "6":
                    playerShinobi = createRockLee();
                    System.out.println("✨ Selected Rock Lee! Hard work beats genius! 💥");
                    return;
                case "7":
                    playerShinobi = createCustomShinobi();
                    System.out.println("✨ Created Custom Shinobi: " + playerShinobi.getName() + "! 🍃");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Select 1 to 7.");
            }
        }
    }

    private static Shinobi createNaruto() {
        Shinobi n = new Shinobi("Naruto Uzumaki", Village.KONOHAGAKURE, 90, ShinobiRank.GENIN, 120.0, uzumaki);
        n.learnJutsu(new Taijutsu("Shadow Clone Jutsu", 15, 30.0, 0.0, "Naruto/Konohamaru clones strike in kinetic cohesion."));
        n.learnJutsu(new Ninjutsu("Rasengan", ChakraAffinity.WIND, 50, 75.0, "A spinning sphere of concentrated chakra in the palm."));
        n.learnJutsu(new Ninjutsu("Rasenshuriken", ChakraAffinity.WIND, 65, 95.0, "Naruto's wind-infused spinning blade of chakra that shreads target's cells."));
        n.learnJutsu(new Ninjutsu("Tailed Beast Bomb", ChakraAffinity.WIND, 90, 130.0, "Condenses positive and negative chakra into a massive, god-tier blast."));
        n.addRyo(50);
        return n;
    }

    private static Shinobi createSasuke() {
        Shinobi s = new Shinobi("Sasuke Uchiha", Village.KONOHAGAKURE, 80, ShinobiRank.GENIN, 100.0, uchiha);
        s.learnJutsu(new Ninjutsu("Fireball Jutsu", ChakraAffinity.FIRE, 25, 35.0, "Shoots a giant sphere of fire from the mouth."));
        s.learnJutsu(new Ninjutsu("Chidori", ChakraAffinity.LIGHTNING, 55, 80.0, "Concentrates lightning chakra into the hand, chirping like a thousand birds."));
        s.learnJutsu(new Ninjutsu("Kirin", ChakraAffinity.LIGHTNING, 70, 105.0, "Sasuke guides natural lightning from clouds down in the shape of Kirin."));
        s.learnJutsu(new Ninjutsu("Amaterasu", ChakraAffinity.FIRE, 60, 85.0, "Uchiha Mangekyo Sharingan ability: casts black flames."));
        s.learnJutsu(new Genjutsu("Amenotejikara", 20, 10.0, 0.60, "Sasuke swaps positions via Rinnegan space-time swapping, stunning the target."));
        s.addRyo(50);
        return s;
    }

    private static Shinobi createKakashi() {
        Shinobi k = new Shinobi("Kakashi Hatake", Village.KONOHAGAKURE, 85, ShinobiRank.JONIN, 110.0, null);
        k.setCustomAffinity(ChakraAffinity.LIGHTNING);
        k.learnJutsu(new Ninjutsu("Lightning Blade", ChakraAffinity.LIGHTNING, 45, 65.0, "Kakashi's Raikiri, lightning focused for a high-speed piercing jab."));
        k.learnJutsu(new Ninjutsu("Earth Spike", ChakraAffinity.EARTH, 18, 25.0, "Raises sharp rock spikes from the ground."));
        k.learnJutsu(new Ninjutsu("Water Dragon Bullet", ChakraAffinity.WATER, 45, 65.0, "Shapes a giant dragon out of water to crush the opponent."));
        k.learnJutsu(new Genjutsu("Kamui", 50, 30.0, 0.80, "Kakashi warps space around target's limbs, causing damage and a heavy stun."));
        k.learnJutsu(new Taijutsu("1000 Years of Death", 0, 15.0, 0.0, "Comedic finger-jab from behind."));
        k.addRyo(100);
        return k;
    }

    private static Shinobi createGaara() {
        Shinobi g = new Shinobi("Gaara", Village.SUNAGAKURE, 90, ShinobiRank.GENIN, 110.0, kazekage);
        g.learnJutsu(new Ninjutsu("Sand Coffin & Burial", ChakraAffinity.EARTH, 45, 65.0, "Gaara binds target in sand and implodes it under extreme pressure."));
        g.learnJutsu(new Ninjutsu("Sand Tsunami", ChakraAffinity.EARTH, 75, 95.0, "Creates a massive tidal wave of sand to crush opponents."));
        g.learnJutsu(new Ninjutsu("Wind Cutter", ChakraAffinity.WIND, 20, 28.0, "Slashes the opponent with compressed wind blades."));
        return g;
    }

    private static Shinobi createItachi() {
        Shinobi i = new Shinobi("Itachi Uchiha", Village.KONOHAGAKURE, 100, ShinobiRank.JONIN, 90.0, uchiha);
        i.learnJutsu(new Genjutsu("Tsukuyomi", 60, 45.0, 0.90, "Itachi traps target in a nightmare world where time is controlled, heavily stunning them."));
        i.learnJutsu(new Ninjutsu("Amaterasu", ChakraAffinity.FIRE, 60, 85.0, "Casts black flames."));
        i.learnJutsu(new Genjutsu("Crow Clone Jutsu", 20, 10.0, 0.50, "Itachi disperses into a flock of crows to confuse and stun the enemy."));
        return i;
    }

    private static Shinobi createRockLee() {
        Shinobi r = new Shinobi("Rock Lee", Village.KONOHAGAKURE, 30, ShinobiRank.GENIN, 130.0, null);
        r.setCustomAffinity(ChakraAffinity.WIND);
        r.learnJutsu(new Taijutsu("Drunken Fist", 0, 45.0, 0.0, "Lee's unpredictable, high-velocity drunken movements."));
        r.learnJutsu(new Taijutsu("Leaf Hurricane", 0, 32.0, 0.0, "A spinning low taijutsu kick sweeping the opponent off their feet."));
        r.learnJutsu(new Taijutsu("Primary Lotus", 10, 55.0, 15.0, "A dangerous pile-driver that causes fatigue to the user."));
        r.learnJutsu(new Taijutsu("Hidden Lotus", 20, 90.0, 35.0, "Opening the Five Gates to strike at extreme speeds, severely exhausting the user."));
        return r;
    }

    private static Shinobi createCustomShinobi() {
        System.out.println("\n🔨 CUSTOM CHARACTER CREATOR 🔨");
        String name = "";
        while (name.isEmpty()) {
            name = getSafeStringInput("Enter Character Name: ");
        }

        System.out.println("Select Village:");
        System.out.println(" 1. Konohagakure (Leaf)");
        System.out.println(" 2. Kumogakure (Cloud)");
        System.out.println(" 3. Iwagakure (Stone)");
        System.out.println(" 4. Kirigakure (Mist)");
        System.out.println(" 5. Sunagakure (Sand)");
        int vChoice = getSafeIntInput("Village choice (1-5): ", 1, 5);
        Village village = Village.values()[vChoice - 1];

        System.out.println("Select Clan (Inherits stats multiplier and elemental affinity):");
        System.out.println(" 1. Uzumaki (+25% HP, +20% CP - Wind)");
        System.out.println(" 2. Uchiha (+15% Attack - Fire)");
        System.out.println(" 3. Senju (+25% HP, +15% Attack - Earth)");
        System.out.println(" 4. Hyuga (+20% Defense - Wind)");
        System.out.println(" 5. Kazekage (+20% Defense - Wind)");
        System.out.println(" 6. None (Balanced starting stats)");
        int cChoice = getSafeIntInput("Clan choice (1-6): ", 1, 6);
        Clan clan = null;
        ChakraAffinity customAffinity = ChakraAffinity.WIND;

        switch (cChoice) {
            case 1: clan = uzumaki; break;
            case 2: clan = uchiha; break;
            case 3: clan = senju; break;
            case 4: clan = hyuga; break;
            case 5: clan = kazekage; break;
            case 6:
                System.out.println("Select Custom Chakra Affinity:");
                System.out.println(" 1. Fire  2. Wind  3. Water  4. Lightning  5. Earth");
                int aChoice = getSafeIntInput("Affinity choice (1-5): ", 1, 5);
                customAffinity = ChakraAffinity.values()[aChoice - 1];
                break;
        }

        // Generate base stats
        int chakra = 70;
        double health = 100.0;
        Shinobi custom = new Shinobi(name, village, chakra, ShinobiRank.ACADEMY_STUDENT, health, clan);
        
        if (clan == null) {
            custom.setCustomAffinity(customAffinity);
        }

        // Give starter Jutsus based on affinity
        custom.learnJutsu(new Taijutsu("Dynamic Entry", 0, 20.0, 0.0, "Basic aerial flying kick."));
        ChakraAffinity aff = custom.getChakraAffinity();
        switch (aff) {
            case FIRE:
                custom.learnJutsu(new Ninjutsu("Fireball Jutsu", ChakraAffinity.FIRE, 25, 35.0, "Small ball of flame."));
                break;
            case WIND:
                custom.learnJutsu(new Ninjutsu("Wind Cutter", ChakraAffinity.WIND, 20, 28.0, "Slicing wind blades."));
                break;
            case WATER:
                custom.learnJutsu(new Ninjutsu("Water Jet", ChakraAffinity.WATER, 15, 22.0, "High pressure water jet."));
                break;
            case LIGHTNING:
                custom.learnJutsu(new Ninjutsu("Spark Strike", ChakraAffinity.LIGHTNING, 20, 30.0, "Discharges lightning."));
                break;
            case EARTH:
                custom.learnJutsu(new Ninjutsu("Earth Spike", ChakraAffinity.EARTH, 18, 25.0, "Spikes from the ground."));
                break;
        }

        return custom;
    }

    private static void startSingleBattle() {
        System.out.println("\n⚔️ SELECT AKATSUKI OPPONENT ⚔️");
        System.out.println(" [1] Hidan - The Immortal Jashinist (Level 2)");
        System.out.println(" [2] Konan - Angel of Ame (Level 6)");
        System.out.println(" [3] Deidara - Explosive Artist (Level 14)");
        System.out.println(" [4] Sasori of the Red Sand - Puppet Master (Level 28)");
        System.out.println(" [5] Kisame Hoshigaki - Tailless Tailed Beast (Level 45)");
        System.out.println(" [6] Pain (Nagato) - God of Shinobi (Level 70)");
        System.out.println(" [0] Cancel");
        
        int diff = getSafeIntInput("Choose opponent (0-6): ", 0, 6);
        if (diff == 0) return;

        Shinobi cpu = switch (diff) {
            case 1 -> CPUOpponents.hidan();
            case 2 -> CPUOpponents.konan();
            case 3 -> CPUOpponents.deidara();
            case 4 -> CPUOpponents.sasori();
            case 5 -> CPUOpponents.kisame();
            case 6 -> CPUOpponents.pain();
            default -> null;
        };

        Battle battle = new Battle(playerShinobi, cpu, true, scanner);
        battle.start();
    }

    private static void startTournamentMode() {
        System.out.println("\n🏆 WELCOME TO THE AKATSUKI GAUNTLET! 🏆");
        System.out.println("You will fight 4 Akatsuki members consecutively. Your health is restored between rounds.");
        System.out.println("Defeat them all to earn the title of S-Rank shinobi!");
        System.out.print("Are you ready to face the Akatsuki? (y/n): ");
        String ready = scanner.nextLine().trim().toLowerCase();
        if (!ready.equals("y") && !ready.equals("yes")) {
            System.out.println("Returning to main menu.");
            return;
        }

        List<Shinobi> ladder = new ArrayList<>();
        ladder.add(CPUOpponents.tournamentHidan());
        ladder.add(CPUOpponents.tournamentDeidara());
        ladder.add(CPUOpponents.tournamentKisame());
        ladder.add(CPUOpponents.tournamentPain());

        int roundNum = 1;
        boolean cleanSweep = true;

        for (Shinobi opponent : ladder) {
            System.out.println("\n🔥=========================================================🔥");
            System.out.println("       ⚔️ AKATSUKI GAUNTLET ROUND " + roundNum + "/4: VS " + opponent.getName() + " ⚔️");
            System.out.println("🔥=========================================================🔥\n");
            
            Battle battle = new Battle(playerShinobi, opponent, true, scanner);
            Shinobi victor = battle.start();
            
            if (victor != playerShinobi) {
                System.out.println("\n❌ You were defeated by " + opponent.getName() + " in Round " + roundNum + "! Gauntlet failed.");
                cleanSweep = false;
                break;
            }
            
            System.out.println("\n🎉 Victory in Round " + roundNum + "! Press Enter to face the next Akatsuki member...");
            scanner.nextLine();
            roundNum++;
        }

        if (cleanSweep) {
            System.out.println("\n🏆🏅=========================================================🏅🏆");
            System.out.println("             👑 AKATSUKI SLAYER! 👑");
            System.out.println("      You have defeated every member of the Akatsuki!");
            System.out.println("               Bonus Reward: +500 Ryo & 300 EXP");
            System.out.println("🏆🏅=========================================================🏅🏆\n");
            
            playerShinobi.addRyo(500);
            System.out.println(playerShinobi.addExperience(300));
        }
    }

    private static void visitJutsuShop() {
        boolean shopping = true;
        while (shopping) {
            System.out.println(shop.displayShop(playerShinobi));
            System.out.print("Select Jutsu number to purchase (or enter 0 to exit shop): ");
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                shopping = false;
                System.out.println("Leaving the shop.");
            } else {
                try {
                    int index = Integer.parseInt(input) - 1;
                    String result = shop.buyJutsu(index, playerShinobi);
                    System.out.println("\n" + result + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("❌ Please enter a valid number.");
                }
            }
        }
    }

    private static void displayShinobiProfile() {
        System.out.println("\n📜================ SHINOBI DOSSIER ================");
        System.out.println(" Name:      " + playerShinobi.getName());
        System.out.println(" Village:   " + playerShinobi.getVillage());
        System.out.println(" Clan:      " + (playerShinobi.getClan() != null ? playerShinobi.getClan().getName() : "None"));
        System.out.println(" Affinity:  " + playerShinobi.getChakraAffinity());
        System.out.println(" Rank:      " + playerShinobi.getRank());
        System.out.println(" Level:     " + playerShinobi.getLevel());
        System.out.println(" Experience:" + playerShinobi.getExperience() + "/" + playerShinobi.getExpNeededForNextLevel() + " EXP");
        System.out.println(" Balance:   " + playerShinobi.getRyo() + " Ryo");
        System.out.println("---------------------------------------------------");
        System.out.printf(" Base HP:    %.1f / %.1f\n", playerShinobi.getCurrentHealth(), playerShinobi.getMaxHealth());
        System.out.printf(" Base CP:    %d / %d\n", playerShinobi.getCurrentChakra(), playerShinobi.getMaxChakra());
        System.out.printf(" Attack Pwr: %.2f (Multiplier: %.2fx)\n", playerShinobi.getAttackPower(), playerShinobi.getAttackMultiplier());
        System.out.printf(" DefensePwr: %.2f (Multiplier: %.2fx)\n", playerShinobi.getDefensePower(), playerShinobi.getDefenseMultiplier());
        System.out.printf(" Speed:      %.1f\n", playerShinobi.getSpeed());
        System.out.println("---------------------------------------------------");
        System.out.println(" Known Jutsus:");
        if (playerShinobi.getKnownJutsus().isEmpty()) {
            System.out.println("   No jutsu learned yet!");
        } else {
            for (Jutsu j : playerShinobi.getKnownJutsus()) {
                System.out.println("   🥋 " + j.getName() + " (" + j.getType() + ")");
                System.out.println("      Cost: " + j.getChakraCost() + " | Dmg: " + j.getDamage());
                System.out.println("      Desc: " + j.getDescription());
            }
        }
        System.out.println("===================================================\n");
        System.out.println("Press Enter to return...");
        scanner.nextLine();
    }

    private static void startMultiplayerMode() {
        System.out.println("\n🌐 MULTIPLAYER ONLINE MODE 🌐");
        System.out.println("Connect to a remote game server and battle other live players!");
        String ip = getSafeStringInput("Enter Server IP Address (default: localhost): ");
        if (ip.isEmpty()) {
            ip = "localhost";
        }
        
        System.out.print("Enter Server Port (default: 9000): ");
        String portStr = scanner.nextLine().trim();
        int port = 9000;
        if (!portStr.isEmpty()) {
            try {
                port = Integer.parseInt(portStr);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid port entered. Using default port 9000.");
            }
        }
        
        GameClient client = new GameClient(ip, port, playerShinobi);
        client.start(scanner);
    }

    private static String getSafeStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getSafeIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("❌ Out of range. Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }
    }
}
