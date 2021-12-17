package army_battle;

import army_battle.unit.Knight;
import army_battle.unit.Vampire;
import army_battle.unit.Warrior;
import army_battle.weapon.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {


    @Test
    void validFightTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();

        boolean expResult = true;
        boolean result = Battle.fight(chuck, bruce);
        assertEquals(expResult, result);
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
    }

    @Test
    void failFightTest() {
        var carl = new Knight();
        var dave = new Warrior();

        boolean expResult = false;
        boolean result = Battle.fight(dave, carl);
        assertEquals(expResult, result);
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    void validArmyFightTest() {
        Army myArmy = new Army().addUnits("Knight", 3);
        Army enemyArmy = new Army().addUnits("Warrior", 3);
        Army army_3 = new Army().addUnits("Warrior", 20).addUnits("Knight", 5);
        Army army_4 = new Army().addUnits("Warrior", 30);

        assertTrue(Battle.fight(myArmy, enemyArmy));
        assertFalse(Battle.fight(army_3, army_4));
    }

    @ParameterizedTest
    @CsvSource({"1,2,false", "2,3,false", "5,7,false", "20,21,true", "10,11,true", "11,7,true"})
    void battleOfTwoArmiesWithoutDefender(int firstArmyCount, int secondArmyCount, boolean expected) {
        Army army_1 = new Army().addUnits("Warrior", firstArmyCount);
        Army army_2 = new Army().addUnits("Warrior", secondArmyCount);
        assertEquals(expected, Battle.fight(army_1, army_2));
    }


    @ParameterizedTest
    @CsvSource({"4,5,5,4,true", "5,4,20,21,true", "5,10,10,5,true", "2,1,1,5,false"})
    void battleOfTwoArmiesWithDefender(int firstArmyFirstDefenderCount, int firstArmySecondDefenderCount,
                                       int firstArmyWarriorCount, int secondArmyWarriorCount,
                                       boolean expected) {
        Army army_1 = new Army()
                .addUnits("Defender", firstArmyFirstDefenderCount)
                .addUnits("Defender", firstArmySecondDefenderCount)
                .addUnits("Warrior", firstArmyWarriorCount);
        Army army_2 = new Army()
                .addUnits("Warrior", secondArmyWarriorCount);
        assertEquals(expected, Battle.fight(army_1, army_2));
    }

    @ParameterizedTest
    @CsvSource({"7,5,6,6,6,6,false", "4,2,3,4,4,3,false", "4,11,3,4,4,13,true", "8,9,3,4,4,13,true"})
    void battleOfTwoArmiesWithVampire(int firstArmyWarriorCount, int firstArmyDefenderCount, int firstArmyVampireCount,
                                      int secondArmyWarriorCount, int secondArmyDefenderCount, int secondArmyVampireCount,
                                      boolean expected) {
        Army army_1 = new Army()
                .addUnits("Defender", firstArmyDefenderCount)
                .addUnits("Vampire", firstArmyVampireCount)
                .addUnits("Warrior", firstArmyWarriorCount);
        Army army_2 = new Army()
                .addUnits("Warrior", secondArmyWarriorCount)
                .addUnits("Defender", secondArmyDefenderCount)
                .addUnits("Vampire", secondArmyVampireCount);
        assertEquals(expected, Battle.fight(army_1, army_2));
    }

    @ParameterizedTest
    @CsvSource({"5,3,4,2,4,4,6,5,false", "7,3,4,2,4,4,6,4,true"})
    void battleOfTwoArmiesWithLancer(int firstArmyLancerCount, int firstArmyVampireCount,
                                            int firstArmyWarriorCount, int firstArmyDefenderCount,
                                            int secondArmyWarriorCount, int secondArmyDefenderCount,
                                            int secondArmyVampireCount, int secondArmyLancerCount,
                                            boolean expected) {
        Army army_1 = new Army()
                .addUnits("Lancer", firstArmyLancerCount)
                .addUnits("Vampire", firstArmyVampireCount)
                .addUnits("Warrior", firstArmyWarriorCount)
                .addUnits("Defender", firstArmyDefenderCount);
        Army army_2 = new Army()
                .addUnits("Warrior", secondArmyWarriorCount)
                .addUnits("Defender", secondArmyDefenderCount)
                .addUnits("Vampire", secondArmyVampireCount)
                .addUnits("Lancer", secondArmyLancerCount);
        assertEquals(expected, Battle.fight(army_1, army_2));
    }

    @DisplayName("Healer testing with param battle Checkio, 9 units vs 13")
    @Test
    void battleTestHealerCheckio() {
        //arrange
        Army myArmy = new Army();
        myArmy.addUnits("Defender", 2);
        myArmy.addUnits("Healer", 1);
        myArmy.addUnits("Vampire", 2);
        myArmy.addUnits("Lancer", 2);
        myArmy.addUnits("Healer", 1);
        myArmy.addUnits("Warrior", 1);

        Army enemyArmy = new Army();
        enemyArmy.addUnits("Warrior", 2);
        enemyArmy.addUnits("Lancer", 4);
        enemyArmy.addUnits("Healer", 1);
        enemyArmy.addUnits("Defender", 2);
        enemyArmy.addUnits("Vampire", 3);
        enemyArmy.addUnits("Healer", 1);

        var result = Battle.fight(myArmy, enemyArmy);

        assertFalse(result);
    }

    @DisplayName("Healer testing with param battle Checkio #2, 9 units vs 13")
    @Test
    void battleTestHealerCheckioSecond() {
        Army myArmy = new Army();
        myArmy.addUnits("Warrior", 1);
        myArmy.addUnits("Lancer", 1);
        myArmy.addUnits("Healer", 1);
        myArmy.addUnits("Defender", 2);

        Army enemyArmy = new Army();
        enemyArmy.addUnits("Vampire", 3);
        enemyArmy.addUnits("Warrior", 1);
        enemyArmy.addUnits("Healer", 1);
        enemyArmy.addUnits("Lancer", 2);

        var result = Battle.fight(myArmy, enemyArmy);

        assertTrue(result);
    }

    @Test
    void battleTestHealerGitHub() {
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

        var result = Battle.fight(army_1, army_2);

        assertFalse(result);
    }

    @Test
    void battleTestHealerCheckioThird() {
        Army myArmy = new Army()
                .addUnits("Defender", 2)
                .addUnits("Healer", 1)
                .addUnits("Vampire", 2)
                .addUnits("Lancer", 2)
                .addUnits("Healer", 1)
                .addUnits("Warrior", 1);

        Army enemyArmy = new Army()
                .addUnits("Warrior", 2)
                .addUnits("Lancer", 4)
                .addUnits("Healer", 1)
                .addUnits("Defender", 2)
                .addUnits("Vampire", 3)
                .addUnits("Healer", 1);

        var result = Battle.fight(myArmy, enemyArmy);

        assertFalse(result);
    }

    @Test
    void battleTestStraightFightFirst() {
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


        var result = Battle.straightFight(army_1, army_2);

        assertFalse(result);
    }

    @Test
    void battleTestStraightFightSecond() {
        Army army_1 = new Army()
                .addUnits("Lancer", 7)
                .addUnits("Vampire", 3)
                .addUnits("Warrior", 4)
                .addUnits("Defender", 2);
        Army army_2 = new Army()
                .addUnits("Warrior", 4)
                .addUnits("Defender", 4)
                .addUnits("Vampire", 6)
                .addUnits("Lancer", 4);


        var result = Battle.straightFight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestStraightFightThird() {
        Army army_1 = new Army()
                .addUnits("Lancer", 7)
                .addUnits("Vampire", 3)
                .addUnits("Healer", 1)
                .addUnits("Warrior", 4)
                .addUnits("Healer", 1)
                .addUnits("Defender", 2);
        Army army_2 = new Army()
                .addUnits("Warrior", 4)
                .addUnits("Defender", 4)
                .addUnits("Healer", 1)
                .addUnits("Vampire", 6)
                .addUnits("Lancer", 4);


        var result = Battle.straightFight(army_1, army_2);

        assertFalse(result);
    }

    @Test
    void battleTestStraightFightForth() {
        Army army_1 = new Army()
                .addUnits("Lancer", 4)
                .addUnits("Warrior", 3)
                .addUnits("Healer", 1)
                .addUnits("Warrior", 4)
                .addUnits("Healer", 1)
                .addUnits("Knight", 2);
        Army army_2 = new Army()
                .addUnits("Warrior", 4)
                .addUnits("Defender", 4)
                .addUnits("Healer", 1)
                .addUnits("Vampire", 2)
                .addUnits("Lancer", 4);


        var result = Battle.straightFight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestWeaponFirst() {
        Warrior unit1 = new Warrior();
        Warrior unit2 = new Vampire();

        Weapon weapon1 = Weapon.builder()
                .health(-10)
                .attack(5)
                .vampirism(40).build();

        Weapon weapon2 = Sword.getInstance();

        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        var result = Battle.fight(unit1, unit2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWeaponFirst() {
        Weapon weapon1 = MagicWand.getInstance();
        Weapon weapon2 = GreatAxe.getInstance();

        Army army_1 = new Army()
                .addUnits("Knight", 1)
                .addUnits("Lancer", 1);
        Army army_2 = new Army()
                .addUnits("Vampire", 1)
                .addUnits("Healer", 1);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon2);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon2);

        var result = Battle.fight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWeaponSecond() {
        Weapon weapon1 = Sword.getInstance();
        Weapon weapon2 = GreatAxe.getInstance();

        Army army_1 = new Army()
                .addUnits("Defender", 1)
                .addUnits("Warrior", 1);
        Army army_2 = new Army()
                .addUnits("Knight", 1)
                .addUnits("Healer", 1);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon2);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon2);

        var result = Battle.fight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWeaponThird() {
        Weapon weapon1 = Katana.getInstance();

        Army army_1 = new Army()
                .addUnits("Defender", 2);
        Army army_2 = new Army()
                .addUnits("Knight", 1)
                .addUnits("Vampire", 1);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon1);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon1);

        var result = Battle.fight(army_1, army_2);

        assertFalse(result);
    }

    @Test
    void battleTestArmyFightWithWeaponForth() {
        Weapon weapon1 = Weapon.builder()
                .health(20)
                .attack(6)
                .defence(1)
                .vampirism(40)
                .healPower(-2).build();
        Weapon weapon2 = Weapon.builder()
                .health(20)
                .attack(-2)
                .defence(2)
                .vampirism(-55)
                .healPower(3).build();

        Army army_1 = new Army()
                .addUnits("Knight", 3);
        Army army_2 = new Army()
                .addUnits("Warrior", 1)
                .addUnits("Defender", 2);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon1);
        units1.get(2).equipWeapon(weapon2);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon2);
        units2.get(1).equipWeapon(weapon2);

        var result = Battle.fight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWeaponFifth() {
        Weapon weapon1 = Weapon.builder()
                .health(-20)
                .attack(1)
                .defence(1)
                .vampirism(40)
                .healPower(-2).build();
        Weapon weapon2 = Weapon.builder()
                .health(20)
                .attack(2)
                .defence(2)
                .vampirism(-55)
                .healPower(3).build();

        Army army_1 = new Army()
                .addUnits("Vampire", 3);
        Army army_2 = new Army()
                .addUnits("Warrior", 1)
                .addUnits("Defender", 2);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon1);
        units1.get(2).equipWeapon(weapon2);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon2);
        units2.get(1).equipWeapon(weapon2);

        var result = Battle.straightFight(army_1, army_2);

        assertFalse(result);
    }

    // add rookie
    @Test
    void battleTestArmyFightWithWeaponSixth() {
        Weapon weapon1 = Katana.getInstance();
        Weapon weapon2 = Shield.getInstance();

        Army army_1 = new Army()
                .addUnits("Vampire", 2)
                .addUnits("Rookie", 2);
        Army army_2 = new Army()
                .addUnits("Warrior", 1)
                .addUnits("Defender", 2);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon1);
        units1.get(2).equipWeapon(weapon2);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon2);
        units2.get(1).equipWeapon(weapon2);

        var result = Battle.straightFight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWeaponSeventh() {
        Weapon weapon1 = Sword.getInstance();
        Weapon weapon2 = GreatAxe.getInstance();

        Army army_1 = new Army()
                .addUnits("Vampire", 3);
        Army army_2 = new Army()
                .addUnits("Warrior", 1)
                .addUnits("Defender", 1);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon2);
        units1.get(1).equipWeapon(weapon2);
        units1.get(2).equipWeapon(weapon2);

        units2.get(0).equipWeapon(weapon1);
        units2.get(1).equipWeapon(weapon1);

        var result = Battle.straightFight(army_1, army_2);

        assertTrue(result);
    }

    // add rookie
    @Test
    void battleTestArmyFightWithWeaponEight() {
        Weapon weapon1 = Katana.getInstance();
        Weapon weapon2 = MagicWand.getInstance();

        Army army_1 = new Army()
                .addUnits("Rookie", 3);
        Army army_2 = new Army()
                .addUnits("Defender", 1)
                .addUnits("Healer", 1);

        List<Warrior> units1 = army_1.getUnits();
        List<Warrior> units2 = army_2.getUnits();

        units1.get(0).equipWeapon(weapon1);
        units1.get(1).equipWeapon(weapon1);
        units1.get(2).equipWeapon(weapon1);

        units2.get(0).equipWeapon(weapon2);
        units2.get(1).equipWeapon(weapon2);

        var result = Battle.straightFight(army_1, army_2);

        assertFalse(result);
    }

    @Test
    void battleTestArmyFightWithWarlordFirst() {
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

        var result = Battle.fight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWarlordSecond() {
        Army army_1 = new Army()
                .addUnits("Warrior", 2)
                .addUnits("Lancer", 2)
                .addUnits("Defender", 1)
                .addUnits("Warlord", 3);
        Army army_2 = new Army()
                .addUnits("Warlord", 2)
                .addUnits("Vampire", 1)
                .addUnits("Healer", 5)
                .addUnits("Knight", 2);

        army_1.moveUnits();
        army_2.moveUnits();

        var result = Battle.fight(army_1, army_2);

        assertFalse(result);
    }

    @Test
    void battleTestArmyFightWithWarlordThird() {
        Army army_1 = new Army()
                .addUnits("Warrior", 2)
                .addUnits("Lancer", 3)
                .addUnits("Defender", 1)
                .addUnits("Warlord", 4);

        Army army_2 = new Army()
                .addUnits("Warlord", 1)
                .addUnits("Vampire", 1)
                .addUnits("Rookie", 1)
                .addUnits("Knight", 1);

        army_1.getUnits().get(0).equipWeapon(Sword.getInstance());
        army_2.getUnits().get(0).equipWeapon(Shield.getInstance());
        army_1.moveUnits();
        army_2.moveUnits();

        var result = Battle.fight(army_1, army_2);

        assertTrue(result);
    }

    @Test
    void battleTestArmyFightWithWarlordForth() {
        Army army_1 = new Army()
                .addUnits("Warrior", 2)
                .addUnits("Lancer", 3)
                .addUnits("Defender", 1)
                .addUnits("Warlord", 1);

        Army army_2 = new Army()
                .addUnits("Warlord", 5)
                .addUnits("Vampire", 1)
                .addUnits("Rookie", 1)
                .addUnits("Knight", 1);

        army_1.getUnits().get(0).equipWeapon(Sword.getInstance());
        army_2.getUnits().get(0).equipWeapon(Shield.getInstance());
        army_1.moveUnits();
        army_2.moveUnits();

        var result = Battle.straightFight(army_1, army_2);

        assertFalse(result);
    }
}