package army_battle;

public class Warrior {
    private int health;
    private static int attack = 5;

    public Warrior(int health) {
        this.health = health;
    }

    public Warrior() {
        this.health = 50;
    }

    public static Warrior of(String clazz) {
        switch (clazz) {
            case "Warrior":
                return new Warrior(50);
            case "Knight":
                return new Knight();
            case "Defender":
                return new Defender();
            default:
                throw new IllegalArgumentException("Unknown Warrior type: " + clazz);
        }
    }

    int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public int getAmountOfDamageFrom(Warrior warrior) {
        return getHealth() - warrior.getAttack();
    }

    public void getDamageFrom(Warrior warrior) {
        setHealth(getAmountOfDamageFrom(warrior));
    }

    public void attack(Warrior warrior) {
        warrior.getDamageFrom(this);
    }

}
