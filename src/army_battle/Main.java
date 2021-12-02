package army_battle;

public class Main {
	public static void main(String[] args) {
		Warrior warrior = new Warrior();
		Warrior knight = new Knight();

		System.out.println(Battle.fight(warrior, knight));
	}
}
