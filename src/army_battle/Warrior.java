package army_battle;

import java.util.Objects;

public class Warrior implements AttackCapable {
    private int health;
    private static int initHealth = 50;
    private static int attack = 5;

    private Warrior behind;

    Warrior(int health) {
        this.health = health;
    }

    public Warrior() {
        this(initHealth);
    }

    public static Warrior of(String clazz) {
        return switch (clazz) {
            case "Warrior" -> new Warrior();
            case "Knight" -> new Knight();
            case "Defender" -> new Defender();
            case "Vampire" -> new Vampire();
            case "Lancer" -> new Lancer();
            case "Healer" -> new Healer();
            default -> throw new IllegalArgumentException("Unknown Warrior type: " + clazz);
        };
    }

    int getHealth() {
        return health;
    }

    public void transmitSignal() {
        if (Objects.nonNull(this.getBehind())) {
            this.getBehind().handleSignalFrom(this);
        }
    }

    public void handleSignalFrom(Warrior warrior) {
        if (Objects.nonNull(warrior.getBehind())) {
            warrior.getBehind().transmitSignal();
        }
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void getDamageFrom(AttackCapable warrior) {
        setHealth(getHealth() - warrior.getAttack());
    }

    public void attack(Warrior warrior) {
        warrior.getDamageFrom(this);
        transmitSignal();
    }

    protected Warrior getBehind() {
        return behind;
    }

    protected void setBehind(Warrior behind) {
        this.behind = behind;
    }

    protected int getInitHealth() {
        return initHealth;
    }
}
