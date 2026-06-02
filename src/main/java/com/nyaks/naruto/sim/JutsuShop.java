package main.java.com.nyaks.naruto.sim;

import java.util.ArrayList;
import java.util.List;
import main.java.com.nyaks.naruto.sim.Jutsu.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;
import main.java.com.nyaks.naruto.sim.enums.ChakraAffinity;

public class JutsuShop {
    private List<Jutsu> inventory;
    private List<Integer> prices;

    public JutsuShop() {
        inventory = new ArrayList<>();
        prices = new ArrayList<>();
        initializeShop();
    }

    private void initializeShop() {
        // --- Ninjutsu ---
        addJutsu(new Ninjutsu("Fireball Jutsu", ChakraAffinity.FIRE, 25, 35.0, "Shoots a giant sphere of fire from the mouth."), 50);
        addJutsu(new Ninjutsu("Dragon Fire Technique", ChakraAffinity.FIRE, 40, 55.0, "Fires a dense flame along a wire line."), 100);
        addJutsu(new Ninjutsu("Majestic Destroyer Flame", ChakraAffinity.FIRE, 50, 80.0, "Uchiha style total-war flame wall that incinerates target."), 180);
        
        addJutsu(new Ninjutsu("Wind Cutter", ChakraAffinity.WIND, 20, 28.0, "Slashes the opponent with compressed wind blades."), 40);
        addJutsu(new Ninjutsu("Rasengan", ChakraAffinity.WIND, 50, 75.0, "A spinning sphere of concentrated chakra in the palm."), 200);
        addJutsu(new Ninjutsu("Rasenshuriken", ChakraAffinity.WIND, 65, 95.0, "Naruto's wind-infused spinning blade of chakra that shreads target's cells."), 280);
        addJutsu(new Ninjutsu("Tailed Beast Bomb", ChakraAffinity.WIND, 90, 130.0, "Condenses positive and negative chakra into a massive, god-tier blast."), 350);
        addJutsu(new Ninjutsu("Wind Scythe Jutsu", ChakraAffinity.WIND, 25, 38.0, "Temari's zoning wind drafts slicing opponents from a distance."), 80);
        addJutsu(new Ninjutsu("Quick Beheading Dance", ChakraAffinity.WIND, 60, 85.0, "Summons Kamatari the weasel to slash the target."), 240);
        
        addJutsu(new Ninjutsu("Water Jet", ChakraAffinity.WATER, 15, 22.0, "Shoots a high-pressure stream of water."), 35);
        addJutsu(new Ninjutsu("Water Dragon Bullet", ChakraAffinity.WATER, 45, 65.0, "Shapes a giant dragon out of water to crush the opponent."), 130);
        
        addJutsu(new Ninjutsu("Spark Strike", ChakraAffinity.LIGHTNING, 20, 30.0, "Zaps the target with electric sparks."), 45);
        addJutsu(new Ninjutsu("Chidori", ChakraAffinity.LIGHTNING, 55, 80.0, "Concentrates lightning chakra into the hand, chirping like a thousand birds."), 220);
        addJutsu(new Ninjutsu("Lightning Blade", ChakraAffinity.LIGHTNING, 45, 65.0, "Kakashi's Raikiri, lightning focused for a high-speed piercing jab."), 160);
        addJutsu(new Ninjutsu("Kirin", ChakraAffinity.LIGHTNING, 70, 105.0, "Sasuke guides natural lightning from clouds down in the shape of Kirin."), 300);
        
        addJutsu(new Ninjutsu("Earth Spike", ChakraAffinity.EARTH, 18, 25.0, "Raises sharp rock spikes from the ground."), 40);
        addJutsu(new Ninjutsu("Earth Golem", ChakraAffinity.EARTH, 48, 60.0, "Summons a large rock golem to strike the opponent."), 120);
        addJutsu(new Ninjutsu("Sand Coffin & Burial", ChakraAffinity.EARTH, 45, 65.0, "Gaara binds target in sand and implodes it under extreme pressure."), 150);
        addJutsu(new Ninjutsu("Sand Tsunami", ChakraAffinity.EARTH, 75, 95.0, "Creates a massive tidal wave of sand to crush opponents."), 260);
        addJutsu(new Ninjutsu("Tengai Shinsei", ChakraAffinity.EARTH, 95, 140.0, "Madara drops a giant meteor from the atmosphere onto the arena."), 400);
        addJutsu(new Ninjutsu("Snake Hands", ChakraAffinity.EARTH, 25, 35.0, "Orochimaru summons multiple snakes from his sleeves to constrict target."), 90);
        addJutsu(new Ninjutsu("Edo Tensei", ChakraAffinity.EARTH, 80, 115.0, "Reanimates legendary warriors to strike down the opponent."), 320);

        // --- Taijutsu ---
        addJutsu(new Taijutsu("Dynamic Entry", 0, 20.0, 0.0, "A flying jump-kick targeting the opponent's face."), 30);
        addJutsu(new Taijutsu("Leaf Hurricane", 0, 32.0, 0.0, "A spinning low taijutsu kick sweeping the opponent off their feet."), 55);
        addJutsu(new Taijutsu("Primary Lotus", 10, 55.0, 15.0, "A dangerous pile-driver that causes fatigue to the user."), 100);
        addJutsu(new Taijutsu("Hidden Lotus", 20, 90.0, 35.0, "Opening the Five Gates to strike at extreme speeds, severely exhausting the user."), 200);
        addJutsu(new Taijutsu("Drunken Fist", 0, 45.0, 0.0, "Lee's unpredictable, high-velocity drunken movements."), 140);
        addJutsu(new Taijutsu("Eight Trigrams 64 Palms", 15, 75.0, 0.0, "Neji's Gentle Fist strikes 64 tenketsu pathways, disabling chakra flow."), 220);
        addJutsu(new Taijutsu("Revolving Heaven", 30, 45.0, 0.0, "Neji releases chakra while spinning rapidly, creating absolute defense."), 130);
        addJutsu(new Taijutsu("Flying Swallow", 15, 45.0, 0.0, "Asuma infuses wind chakra into trench knives, extending blade length."), 110);
        addJutsu(new Taijutsu("Fang Over Fang", 10, 40.0, 5.0, "Kiba and Akamaru spin like drills to pierce the opponent."), 95);
        addJutsu(new Taijutsu("Two-Headed Wolf", 30, 70.0, 20.0, "Transforms into a giant multi-headed beast, crushing the enemy."), 210);
        addJutsu(new Taijutsu("Shadow Clone Jutsu", 15, 30.0, 0.0, "Naruto/Konohamaru clones strike in kinetic cohesion."), 70);

        // --- Genjutsu ---
        addJutsu(new Genjutsu("Tree Bind Death", 25, 15.0, 0.60, "Makes the target see themselves bound to a tree, stunning them."), 80);
        addJutsu(new Genjutsu("Temple of Nirvana", 40, 22.0, 0.75, "Causes a rain of white feathers, putting the target to sleep (stun)."), 140);
        addJutsu(new Genjutsu("Tsukuyomi", 60, 45.0, 0.90, "Itachi traps target in a nightmare world where time is controlled, heavily stunning them."), 260);
        addJutsu(new Genjutsu("Amenotejikara", 20, 10.0, 0.60, "Sasuke swaps positions via Rinnegan space-time swapping, stunning the target."), 120);
        addJutsu(new Genjutsu("Kamui", 50, 30.0, 0.80, "Kakashi warps space around target's limbs, causing damage and a heavy stun."), 240);
        addJutsu(new Genjutsu("Crow Clone Jutsu", 20, 10.0, 0.50, "Itachi disperses into a flock of crows to confuse and stun the enemy."), 100);
        addJutsu(new Genjutsu("Shadow Possession", 25, 10.0, 0.70, "Shikamaru binds the target's shadow, freezing them in place (stun)."), 110);
        addJutsu(new Genjutsu("Shadow Strangle", 40, 35.0, 0.70, "Strangles the target with physical shadow bindings, dealing damage and stunning."), 190);
    }

    private void addJutsu(Jutsu jutsu, int price) {
        inventory.add(jutsu);
        prices.add(price);
    }

    public List<Jutsu> getInventory() {
        return inventory;
    }

    public int getPrice(int index) {
        if (index >= 0 && index < prices.size()) {
            return prices.get(index);
        }
        return -1;
    }

    public String displayShop(Shinobi player) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================================================\n");
        sb.append("                       🍃 KONOHA JUTSU SHOP 🍃                         \n");
        sb.append("========================================================================\n");
        sb.append(" Your Balance: ").append(player.getRyo()).append(" Ryo | Element: ").append(player.getChakraAffinity()).append("\n\n");
        
        for (int i = 0; i < inventory.size(); i++) {
            Jutsu j = inventory.get(i);
            int price = prices.get(i);
            boolean alreadyKnown = player.getKnownJutsus().stream()
                    .anyMatch(k -> k.getName().equalsIgnoreCase(j.getName()));
            
            sb.append(String.format("[%2d] %-25s | Type: %-9s | Price: %3d Ryo", (i + 1), j.getName(), j.getType(), price));
            if (alreadyKnown) {
                sb.append(" (LEARNED)");
            }
            sb.append("\n     - Description: ").append(j.getDescription());
            if (j instanceof Ninjutsu) {
                sb.append(" (Element: ").append(j.getAffinity()).append(", Cost: ").append(j.getChakraCost()).append(" CP, Dmg: ").append(j.getDamage()).append(")");
            } else if (j instanceof Taijutsu) {
                Taijutsu t = (Taijutsu) j;
                sb.append(" (Stamina HP Cost: ").append(t.getHealthCost()).append(", Dmg: ").append(j.getDamage()).append(")");
            } else if (j instanceof Genjutsu) {
                Genjutsu g = (Genjutsu) j;
                sb.append(" (Stun Chance: ").append((int)(g.getStunChance() * 100)).append("%, Cost: ").append(j.getChakraCost()).append(" CP)");
            }
            sb.append("\n------------------------------------------------------------------------\n");
        }
        return sb.toString();
    }

    public String buyJutsu(int index, Shinobi player) {
        if (index < 0 || index >= inventory.size()) {
            return "❌ Invalid selection.";
        }

        Jutsu selection = inventory.get(index);
        int price = prices.get(index);

        boolean alreadyKnown = player.getKnownJutsus().stream()
                .anyMatch(k -> k.getName().equalsIgnoreCase(selection.getName()));

        if (alreadyKnown) {
            return "❌ You already know " + selection.getName() + "!";
        }

        if (player.getRyo() < price) {
            return "❌ You do not have enough Ryo! Need " + price + " Ryo, but you have " + player.getRyo() + " Ryo.";
        }

        player.spendRyo(price);
        player.learnJutsu(selection);
        return "✨ Congratulations! You learned " + selection.getName() + " for " + price + " Ryo!";
    }
}
