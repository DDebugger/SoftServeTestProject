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
    public void attack(Warrior warrior) {
        super.attack(warrior);
        setHealth(getHealth() + warrior.getAmountOfDamageFrom(this) * vampirism / 100);
    }
}
