package army_battle;

import java.util.Objects;

public class Lancer extends Warrior {
    private static int attack = 6;
    private static int decay = 50;

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void attack(Warrior warrior) {
        int healthDecrease = warrior.getHealth();
        super.attack(warrior);
        healthDecrease = healthDecrease - Math.max(0, warrior.getHealth());
        int attackForSecond = healthDecrease * decay / 100;
        if (!Objects.isNull(warrior.getBehind())) {
            warrior.getBehind().getDamageFrom(() -> attackForSecond);
        }
    }
}
