package army_battle;

public class Main {
    public static void main(String[] args) {
		Army army_1 = new Army()
				.addUnits("Lancer", 1)
				.addUnits("Warrior", 3)
				.addUnits("Healer", 1)
				.addUnits("Warrior", 4)
				.addUnits("Healer", 1)
				.addUnits("Knight", 2);
		Army army_2 = new Army()
				.addUnits("Warrior", 4)
				.addUnits("Defender", 4)
				.addUnits("Healer", 1)
				.addUnits("Vampire", 6)
				.addUnits("Lancer", 4);

        Battle.fight(army_1, army_2);
    }
}
