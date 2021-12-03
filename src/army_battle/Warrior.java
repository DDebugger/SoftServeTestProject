package army_battle;

public class Warrior {
    private int health = 50;
    private static int attack = 5;

    public static Warrior of(String clazz) {
        switch (clazz) {
            case "Warrior":
                return new Warrior();
            case "Knight":
                return new Knight();
            default:
                throw new IllegalArgumentException("Unknown Warrior type: " + clazz);
        }
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

    public void getDamage(Warrior warrior) {
        setHealth(getHealth() - warrior.getAttack());
    }

    public void attack(Warrior warrior) {
        warrior.getDamage(this);
    }

}
