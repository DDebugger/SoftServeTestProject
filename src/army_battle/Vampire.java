package army_battle;

public class Vampire extends Warrior {
    private static final int health = 40;
    private static final int attack = 4;
    private static final int vampirism = 50;

    public Vampire() {
        super(health);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    protected int getInitHealth() {
        return health;
    }

    @Override
    public void attack(Warrior warrior) {
        int healthDecrease = warrior.getHealth();
        warrior.getDamageFrom(this);
        healthDecrease = healthDecrease - warrior.getHealth();

        if (getHealth() + healthDecrease * vampirism / 100 <= getInitHealth()) {
            setHealth(getHealth() + healthDecrease * vampirism / 100);
        }else {
            setHealth(getInitHealth());
        }
    }
}
