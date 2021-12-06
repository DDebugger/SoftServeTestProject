package army_battle;

public class Warrior {
    private int health;
    private static int attack = 5;

    public Warrior(int health) {
        this.health = health;
    }

    public Warrior() {
        this(50);
    }

    public static Warrior of(String clazz) {
        return switch (clazz) {
            case "Warrior" -> new Warrior();
            case "Knight" -> new Knight();
            case "Defender" -> new Defender();
            case "Vampire" -> new Vampire();
            default -> throw new IllegalArgumentException("Unknown Warrior type: " + clazz);
        };
    }

    int getHealth() {
        return health;
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

    protected int getAmountOfDamageFrom(Warrior warrior) {
        return warrior.getAttack();
    }

    public void getDamageFrom(Warrior warrior) {
        setHealth(getHealth() - getAmountOfDamageFrom(warrior));
    }

    public void attack(Warrior warrior) {
        warrior.getDamageFrom(this);
    }

}
