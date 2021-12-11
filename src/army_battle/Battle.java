package army_battle;


import java.util.List;

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

    public static boolean straight_fight(Army army1, Army army2) {
        // Строем ширенгу
        army1.lineUp();
        army2.lineUp();

        while (true) {

            var attacker = army1.getFirstWarrior();
            if (attacker.isEmpty()) {
                // Строем колонну
                army2.reorganizeArmyIntoColumn();
                return false;
            }
            var defender = army2.getFirstWarrior();
            if (defender.isEmpty()) {
                // Строем колонну
                army1.reorganizeArmyIntoColumn();
                return true;
            }

            // Убираем мертвецов
            List<Warrior> units1 = army1.getUnits();
            List<Warrior> units2 = army2.getUnits();
            // Сражение
            for (int i = 0; i < Math.min(units1.size(), units2.size()); i++) {
                fight(units1.get(i), units2.get(i));
            }
        }
    }


}