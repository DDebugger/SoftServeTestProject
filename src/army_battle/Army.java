package army_battle;

import java.util.*;

public class Army {
    private List<Warrior> units = new ArrayList<>();

    Army addUnits(String warriorType, int count) {
        final int end;
        end = units.size() + count;
        for (int i = units.size(); i < end; i++) {
            units.add(Warrior.of(warriorType));
            if (units.size() > 1) {
                units.get(i - 1).setBehind(units.get(i));
            }
        }
        return this;
    }

    public Optional<Warrior> getFirstWarrior() {
        return units.stream().filter(Warrior::isAlive).findFirst();
    }
}
