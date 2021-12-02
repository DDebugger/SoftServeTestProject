package army_battle;

public class Warrior {
	private int health = 50;
	private static int attack = 5;

	int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public boolean isAlive() {
		return getHealth() > 0;
	}

	private void getDamage(Warrior warrior) {
		setHealth(getHealth() - warrior.getAttack());
	}
	
	public void attack(Warrior warrior) {
		warrior.getDamage(this);
	}

}
