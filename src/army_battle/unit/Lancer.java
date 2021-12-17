package army_battle.unit;

import java.util.Objects;

public class Lancer extends Warrior {
    private static final int INIT_HEALTH = 50;

    public Lancer() {
        super(INIT_HEALTH);
    }

    @Override
    protected int getInitAttack() {
        return 6;
    }

    @Override
    public void attack(Warrior warrior) {
        int healthDecrease = warrior.getHealth();
        super.attack(warrior);
        healthDecrease = healthDecrease - warrior.getHealth();
        int decay = 50;
        int attackForSecond = healthDecrease * decay / 100;
        if (!Objects.isNull(warrior.getBehind())) {
            warrior.getBehind().getDamageFrom(() -> attackForSecond);
        }
    }
}
