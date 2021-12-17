package army_battle.unit;

import army_battle.weapon.Weapon;

public class Defender extends Warrior implements DefenceCapable {
    private static final int INIT_HEALTH = 60;
    private int defence = 2;

    public Defender() {
        super(INIT_HEALTH);
    }

    @Override
    protected int getInitAttack() {
        return 3;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        this.defence = getDefence() + weapon.getDefence();
    }

    @Override
    public void getDamageFrom(AttackCapable warrior) {
        setHealth(getHealth() - Math.max(0, warrior.getAttack() - defence));
    }

    @Override
    public int getDefence() {
        return defence;
    }
}