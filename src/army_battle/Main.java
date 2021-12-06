package army_battle;

public class Main {
	public static void main(String[] args) {
		Army army_1 = new Army()
				.addUnits("Defender", 5)
				.addUnits("Vampire", 6)
				.addUnits("Warrior", 7);
		Army army_2 = new Army()
				.addUnits("Warrior", 6)
				.addUnits("Defender", 6)
				.addUnits("Vampire", 6);

		System.out.println(Battle.fight(army_1, army_2));
	}
}
