package army_battle;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class Army {
    private final Collection<Warrior> units = new LinkedList<>();

    Army addUnits(String warriorType, int count) {
        for (int i = 0; i < count; i++) {
            units.add(Warrior.of(warriorType));
        }
        return this;
    }

    public Optional<Warrior> getFirstWarrior() {
        return units.stream().filter(Warrior::isAlive).findFirst();
    }
}
