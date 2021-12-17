package army_battle.unit;

public class Rookie extends Warrior {
    private static final int INIT_HEALTH = 50;

    public Rookie() {
        super(INIT_HEALTH);
    }

    @Override
    protected int getInitAttack() {
        return 1;
    }
}
