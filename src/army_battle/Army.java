package army_battle;

import java.util.*;

public class Army {
    private final List<Warrior> units = new ArrayList<>();

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

    public List<Warrior> getUnits() {
        return units.stream().filter(Warrior::isAlive).toList();
    }

    public int getArmySize() {
        return units.stream().filter(Warrior::isAlive).toList().size();
    }

    public void lineUp() {
        for (Warrior warrior : units) {
            warrior.setBehind(null);
        }
    }

    public void reorganizeArmyIntoColumn() {
        for (int i = 0; i < getArmySize(); i++) {
            if (getFirstWarrior().isPresent() && i > 0) {
                getUnits().get(i - 1).setBehind(getUnits().get(i));
            }
        }
    }
}
