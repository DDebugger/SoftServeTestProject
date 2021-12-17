package army_battle.unit;

import army_battle.weapon.Weapon;

public class Healer extends Warrior {
    private static final int INIT_HEALTH = 60;
    private int healingPower = 2;

    public Healer() {
        super(INIT_HEALTH);
    }

    @Override
    public void handleSignalFrom(Warrior warrior) {
        heal(warrior);
        super.transmitSignal();
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        setHealth(getHealth() + weapon.getHealth());
        this.healingPower = this.healingPower + weapon.getHealPower();
    }

    @Override
    protected int getInitAttack() {
        return 0;
    }

    private void heal(Warrior warrior) {
        warrior.setHealth(Math.min(warrior.getHealth() + healingPower, warrior.getInitHealth()));
    }
}
