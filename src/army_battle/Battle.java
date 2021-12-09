package army_battle;


public class Battle {
    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (true) {
            if (warrior1.isAlive()) {
                warrior1.attack(warrior2);
            } else {
                return false;
            }

            if (warrior2.isAlive()) {
                warrior2.attack(warrior1);
            } else {
                return true;
            }
        }
    }

    public static boolean fight(Army army1, Army army2) {
        while (true) {
            var attacker = army1.getFirstWarrior();
            if (attacker.isEmpty()) {
                return false;
            }
            var defender = army2.getFirstWarrior();
            if (defender.isEmpty()) {
                return true;
            }

            fight(attacker.get(), defender.get());
        }
    }

}