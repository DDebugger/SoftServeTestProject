package army_battle;

public class Defender extends Warrior {
    private static int defence = 2;
    private static int attack = 3;

    public Defender() {
        super(60);
    }

//    @Override
//    int getHealth() {
//        return health;
//    }
//
//    @Override
//    protected void setHealth(int health) {
//        Defender.health = health;
//    }

    @Override
    public int getAttack() {
        return attack;
    }


    @Override
    public int getAmountOfDamageFrom(Warrior warrior) {
        if (warrior.getAttack() > defence) {
            return getHealth() - (warrior.getAttack() - defence);
        }else {
            return getHealth();
        }
    }
}
