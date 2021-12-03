package army_battle;

public class Main {
	public static void main(String[] args) {
		Army warrior = new Army().addUnits("Knight", 20);
		Army warrior1 = new Army().addUnits("Warrior", 25);

		System.out.println(Battle.fight(warrior, warrior1));
	}
}
