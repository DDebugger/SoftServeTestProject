package army_battle;


public class Main {
    public static void main(String[] args) {
        Army army_1 = new Army()
                .addUnits("Warlord", 1)
                .addUnits("Warrior", 2)
                .addUnits("Lancer", 2)
                .addUnits("Healer", 2)
                .addUnits("Healer", 1);
        Army army_2 = new Army()
                .addUnits("Warlord", 1)
                .addUnits("Vampire", 1)
                .addUnits("Healer", 2)
                .addUnits("Knight", 2);

        army_1.moveUnits();
        army_2.moveUnits();

        System.out.println(Battle.fight(army_1, army_2));

    }


}
