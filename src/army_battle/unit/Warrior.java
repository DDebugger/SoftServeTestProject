package army_battle.unit;

import army_battle.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Warrior implements AttackCapable {

    private static final int DEFAULT_WARRIOR_HEALTH = 50;
    private static final int DEFAULT_WARRIOR_ATTACK = 5;
    private int initHealth;
    private int health;

    private Warrior behind;
    private final List<Weapon> weaponList = new ArrayList<>();

    Warrior(int health) {
        this.health = health;
        this.initHealth = health;
    }

    public Warrior() {
        this(DEFAULT_WARRIOR_HEALTH);
    }

    public static Warrior of(String clazz) {
        return switch (clazz) {
            case "Warrior" -> new Warrior();
            case "Rookie" -> new Rookie();
            case "Knight" -> new Knight();
            case "Defender" -> new Defender();
            case "Vampire" -> new Vampire();
            case "Lancer" -> new Lancer();
            case "Healer" -> new Healer();
            case "Warlord" -> new Warlord();
            default -> throw new IllegalArgumentException("Unknown Warrior type: " + clazz);
        };
    }

    public int getHealth() {
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
        if (getInitAttack() == 0) return 0;

        return getInitAttack() + weaponList.stream().mapToInt(Weapon::getAttack).sum();
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

    public void setBehind(Warrior behind) {
        this.behind = behind;
    }

    protected int getInitHealth() {
        return initHealth;
    }

    protected int getInitAttack() {
        return DEFAULT_WARRIOR_ATTACK;
    }

    public void equipWeapon(Weapon weapon) {
        this.weaponList.add(weapon);
        this.initHealth = getInitHealth() + weapon.getHealth();
        setHealth(getHealth() + weapon.getHealth());
    }
}
