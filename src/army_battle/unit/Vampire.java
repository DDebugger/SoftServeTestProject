package army_battle.unit;

import army_battle.weapon.Weapon;

public class Vampire extends Warrior {
    private static final int INIT_HEALTH = 40;
    private int vampirism = 50;

    public Vampire() {
        super(INIT_HEALTH);
    }

    @Override
    protected int getInitAttack() {
        return 4;
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        this.vampirism = Math.max(0, this.vampirism + weapon.getVampirism());
    }

    @Override
    public void attack(Warrior warrior) {
        int healthDecrease = warrior.getHealth();
        super.attack(warrior);
        healthDecrease = healthDecrease - warrior.getHealth();

        setHealth(Math.min(getHealth() + healthDecrease * vampirism / 100, getInitHealth()));
    }
}
