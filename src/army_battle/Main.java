package army_battle;

public class Main {
	public static void main(String[] args) {
//		Army warrior = new Army().addUnits("Defender", 20);
//		Army warrior1 = new Army().addUnits("Warrior", 25);
		Army army_1 = new Army()
				.addUnits("Defender", 4)
				.addUnits("Defender", 5)
				.addUnits("Warrior", 5);
		Army army_2 = new Army()
				.addUnits("Warrior", 4);

		System.out.println(Battle.fight(army_1, army_2));
	}
}
