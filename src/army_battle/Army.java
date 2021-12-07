package army_battle;

import java.util.*;

public class Army {
    private final List<Warrior> units = new ArrayList<>();

    Army addUnits(String warriorType, int count) {
        for (int i = 0; i < count; i++) {
            units.add(Warrior.of(warriorType));
            if (i > 0) {
                units.get(i - 1).setBehind(units.get(i));
            }
        }
        return this;
    }

    public Optional<Warrior> getFirstWarrior() {
        return units.stream().filter(Warrior::isAlive).findFirst();
    }
}
