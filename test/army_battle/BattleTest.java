package army_battle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {


    @Test
    public void validFightTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();

        boolean expResult = true;
        boolean result = Battle.fight(chuck, bruce);
        assertEquals(expResult, result);
    }

    @Test
    public void failFightTest() {
        var carl = new Knight();
        var dave = new Warrior();

        boolean expResult = false;
        boolean result = Battle.fight(dave, carl);
        assertEquals(expResult, result);
    }

}