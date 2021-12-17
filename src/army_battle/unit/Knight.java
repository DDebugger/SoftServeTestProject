package army_battle.unit;

public class Knight extends Warrior {
	private static final int INIT_HEALTH = 50;

	public Knight() {
		super(INIT_HEALTH);
	}

	@Override
	protected int getInitAttack() {
		return 7;
	}
}
