package army_battle;

public class Defender extends Warrior {
    private static int health = 60;
    private static int defence = 2;
    private static int attack = 3;

    public Defender() {
        super(health);
    }

    @Override
    protected int getInitHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void getDamageFrom(AttackCapable warrior) {
        setHealth(getHealth() - Math.max(0, warrior.getAttack() - defence));

    }

}