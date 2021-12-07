package army_battle;

public class Main {
	public static void main(String[] args) {
		Army army_1 = new Army()
				.addUnits("Lancer", 5)
				.addUnits("Vampire", 3)
				.addUnits("Warrior", 4)
				.addUnits("Defender", 2);
		Army army_2 = new Army()
				.addUnits("Warrior", 4)
				.addUnits("Defender", 4)
				.addUnits("Vampire", 6)
				.addUnits("Lancer", 5);

		System.out.println(Battle.fight(army_1, army_2));
	}
}
