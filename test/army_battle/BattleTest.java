package army_battle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {


    @Test
    public void validFightTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();

        boolean expResult = true;
        boolean result = Battle.fight(chuck, bruce);
        assertEquals(expResult, result);
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
    }

    @Test
    public void failFightTest() {
        var carl = new Knight();
        var dave = new Warrior();

        boolean expResult = false;
        boolean result = Battle.fight(dave, carl);
        assertEquals(expResult, result);
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    public void validArmyFightTest() {
        Army myArmy = new Army().addUnits("Knight", 3);
        Army enemyArmy = new Army().addUnits("Warrior", 3);
        Army army_3 = new Army().addUnits("Warrior", 20).addUnits("Knight", 5);
        Army army_4 = new Army().addUnits("Warrior", 30);

        assertTrue(Battle.fight(myArmy, enemyArmy));
        assertFalse(Battle.fight(army_3, army_4));
    }

    @ParameterizedTest
    @CsvSource({"1,2,false", "2,3,false", "5,7,false", "20,21,true", "10,11,true", "11,7,true"})
    public void battleOfTwoArmiesWithoutDefender(int firstArmyCount, int secondArmyCount, boolean expected) {
        Army army_1 = new Army().addUnits("Warrior", firstArmyCount);
        Army army_2 = new Army().addUnits("Warrior", secondArmyCount);
        assertEquals(expected, Battle.fight(army_1, army_2));
    }


    @ParameterizedTest
    @CsvSource({"4,5,5,4,true", "5,4,20,21,true", "5,10,10,5,true", "2,1,1,5,false"})
    public void battleOfTwoArmiesWithDefender(int firstArmyFirstDefenderCount, int firstArmySecondDefenderCount,
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
    public void battleOfTwoArmiesWithVampire(int firstArmyWarriorCount, int firstArmyDefenderCount, int firstArmyVampireCount,
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


}