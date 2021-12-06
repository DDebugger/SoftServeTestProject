package army_battle;

public class Defender extends Warrior {
    private static int health = 60;
    private static int defence = 2;
    private static int attack = 3;

    public Defender() {
        super(health);
    }

    @Override
    public int getAttack() {
        return attack;
    }


    @Override
    protected int getAmountOfDamageFrom(Warrior warrior) {
        if (warrior.getAttack() > defence) {
            return warrior.getAttack() - defence;
        }else {
            return getHealth();
        }
    }
}
