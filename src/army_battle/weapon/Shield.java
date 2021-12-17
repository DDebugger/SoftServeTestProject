package army_battle.weapon;

public class Shield extends Weapon {
    private static Shield instance;
    private static final int HEALTH = 20;
    private static final int ATTACK = -1;
    private static final int DEFENCE = 2;
    private static final int VAMPIRISM = 0;
    private static final int HEAL_POWER = 0;

    public Shield() {
        super(HEALTH, ATTACK, DEFENCE, VAMPIRISM, HEAL_POWER);
    }

    public static Shield getInstance() {
        if (instance == null) {
            instance = new Shield();
        }
        return instance;
    }
}
