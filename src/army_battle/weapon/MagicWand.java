package army_battle.weapon;

public class MagicWand extends Weapon {
    private static MagicWand instance;
    private static final int HEALTH = 30;
    private static final int ATTACK = 3;
    private static final int DEFENCE = 0;
    private static final int VAMPIRISM = 0;
    private static final int HEAL_POWER = 3;

    public MagicWand() {
        super(HEALTH, ATTACK, DEFENCE, VAMPIRISM, HEAL_POWER);
    }

    public static MagicWand getInstance() {
        if (instance == null) {
            instance = new MagicWand();
        }
        return instance;
    }
}
