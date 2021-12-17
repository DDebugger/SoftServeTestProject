package army_battle;

import army_battle.unit.Warrior;

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
        int firstArmySize = army1.getArmySize();
        int secondArmySize = army2.getArmySize();

        while (true) {
            if (army1.getArmySize() != firstArmySize) {
                army1.moveUnits();
                army1.lineUp();
                army1.reorganizeArmyIntoColumn();
                firstArmySize = army1.getArmySize();
            }
            if (army2.getArmySize() != secondArmySize) {
                army2.moveUnits();
                army2.lineUp();
                army2.reorganizeArmyIntoColumn();
                secondArmySize = army2.getArmySize();
            }

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

    public static boolean straightFight(Army army1, Army army2) {
        // Строем ширенгу
        army1.lineUp();
        army2.lineUp();

        int firstArmySize = army1.getArmySize();
        int secondArmySize = army2.getArmySize();
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

                if (army1.getArmySize() != firstArmySize) {
                    army1.moveUnits();
                    firstArmySize = army1.getArmySize();
                }
                if (army2.getArmySize() != secondArmySize) {
                    army2.moveUnits();
                    secondArmySize = army2.getArmySize();

                }
            }
        }
    }
}