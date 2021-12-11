package army_battle;

public class Healer extends Warrior {
    private static int attack = 0;
    private static int health = 60;
    private static int healingPower = 2;

    public Healer() {
        super(health);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void handleSignalFrom(Warrior warrior) {
        heal(warrior);
        super.transmitSignal();
    }


    @Override
    protected int getInitHealth() {
        return health;
    }

    private void heal(Warrior warrior) {
        if (warrior.getHealth() + healingPower <= warrior.getInitHealth()) {
            warrior.setHealth(warrior.getHealth() + healingPower);
        } else {
            warrior.setHealth(warrior.getInitHealth());
        }
    }
}
