# 🍥 Naruto Shinobi Battle Simulator 🍃

A rich, interactive, turn-based command-line Battle Simulator built in Java to demonstrate core Object-Oriented Programming (OOP) concepts. Step into the shoes of your favorite legendary ninja or create your own custom shinobi, learn powerful techniques from the Jutsu Shop, and conquer the Chunin Selection Tournament!

---

## 🚀 How to Compile & Run

You can build and run this project using **Maven** (recommended) or direct **Java CLI** commands.

### Option A: Using Maven (Recommended)
1. **Compile**:
   ```bash
   mvn compile
   ```
2. **Run**:
   ```bash
   mvn exec:java
   ```

### Option B: Using Direct Java CLI
1. **Compile**:
   ```bash
   mkdir -p bin && javac -d bin src/main/java/com/nyaks/naruto/sim/enums/*.java \
                              src/main/java/com/nyaks/naruto/sim/Clan/*.java \
                              src/main/java/com/nyaks/naruto/sim/Jutsu/*.java \
                              src/main/java/com/nyaks/naruto/sim/Shinobi/*.java \
                              src/main/java/com/nyaks/naruto/sim/*.java
   ```
2. **Run**:
   ```bash
   java -cp bin main.java.com.nyaks.naruto.sim.Main
   ```

---

## 🛠️ Demonstration of OOP Concepts

This project serves as a showcase of the four pillars of Object-Oriented Programming:

1. **Abstraction**: 
   - `Jutsu` is declared as an `abstract` class. It encapsulates shared properties (name, cost, base damage) but delegates the concrete execution behavior of the technique to its subclasses via the abstract method:
     ```java
     public abstract String use(Shinobi user, Shinobi target);
     ```

2. **Inheritance**:
   - `Ninjutsu`, `Taijutsu`, and `Genjutsu` extend the parent `Jutsu` class. They inherit base fields and add specialized behaviors (e.g. `healthCost` for Taijutsu, `stunChance` for Genjutsu).

3. **Polymorphism**:
   - Method overriding is used when calling the `use()` method on any `Jutsu` reference. The game engine executes different damage calculation and status-effect logic depending on whether the object is an instance of `Ninjutsu`, `Taijutsu`, or `Genjutsu`.

4. **Encapsulation**:
   - All properties on `Shinobi`, `Clan`, and `Jutsu` are declared `private` or protected, exposing modification and access solely through public getters, setters, and controlled state transition methods (like `applyDamage()`, `consumeChakra()`, and `addExperience()`).

---

## 🎮 Key Features

* **Preset Roster**: Choose to play as Naruto Uzumaki, Sasuke Uchiha, Kakashi Hatake, Gaara, Itachi Uchiha, or Rock Lee—each fully custom-tailored with their lore-accurate clans, elements, and starting techniques.
* **Custom Shinobi Creator**: Design your own custom shinobi from scratch. Name them, select their Hidden Village, assign them to a legendary Clan (Uzumaki, Uchiha, Senju, Hyuga, Kazekage), or customize their Chakra Affinity.
* **Tactical Turn-Based Combat**:
  - **Basic Attacks**: Deal physical damage and restore +15 CP (Chakra).
  - **Jutsu Attacks**: Cast powerful elemental ninjutsu, stamina-consuming taijutsu, or disabling genjutsu.
  - **Chakra Charge**: Gather energy to restore +40 CP.
  - **Kawarimi (Substitution)**: Consume 15 CP to prepare a substitution log, granting a 55% chance to dodge the opponent's next attack completely.
* **Elemental Counter System**: Ninjutsu techniques check chakra affinities. 
  - **Fire** 🔥 beats **Wind** 🌪️
  - **Wind** 🌪️ beats **Lightning** ⚡
  - **Lightning** ⚡ beats **Earth** 🪨
  - **Earth** 🪨 beats **Water** 🌊
  - **Water** 🌊 beats **Fire** 🔥
  - *Dominant element deals 1.5x damage, weak elements deal 0.7x damage.*
* **Status Effects & Fatigue**:
  - Genjutsu has a chance to **Stun** the target, making them skip their next turn.
  - Taijutsu (like *Primary* and *Hidden Lotus*) requires no CP, but drains the user's HP to execute massive physical combos.
* **Progression System**: Win duels to gain EXP and level up, increasing base stats. Automatic Rank-up promotions advance your character from Academy Student to Genin, Chunin, Jonin, and Kage.
* **Jutsu Shop**: Spend Ryo coins earned from victories to purchase and master legendary techniques.
* **Chunin Selection Tournament**: Challenge a 4-round gauntlet against increasingly difficult CPU opponents (Konohamaru, Temari, Neji, and Gaara) to become the ultimate Arena Champion!