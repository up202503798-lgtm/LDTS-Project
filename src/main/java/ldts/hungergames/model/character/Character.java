package ldts.hungergames.model.character;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class Character {
    private final String name;
    private final int maxHealth;
    private final Set<Trait> traits;
    private int health;
    private int supplies;
    private Position position;

    public Character(String name, int maxHealth, Set<Trait> traits, int supplies, Position position) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.traits = traits.isEmpty() ? EnumSet.noneOf(Trait.class) : EnumSet.copyOf(traits);
        this.supplies = supplies;
        this.position = position;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getSupplies() { return supplies; }
    public Position getPosition() { return position; }
    public Set<Trait> getTraits() { return Collections.unmodifiableSet(traits); }

    public boolean hasTrait(Trait trait) { return traits.contains(trait); }
    public boolean isAlive() { return health > 0; }

    public void setPosition(Position position) { this.position = position; }

    public void takeDamage(int amount) { health = Math.max(0, health - amount); }
    public void heal(int amount) { health = Math.min(maxHealth, health + amount); }

    public void addSupplies(int amount) { supplies += amount; }
    public void consumeSupplies(int amount) { supplies = Math.max(0, supplies - amount); }
}
