package army_battle;

import army_battle.unit.Warlord;
import army_battle.unit.Warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Army {
    private List<Warrior> units = new ArrayList<>();
    private Warlord warlord = null;

    private boolean isWarlordExists() {
        boolean isWarlordExists = false;
        List<Warrior> listToRemove = new ArrayList<>();
        for (Warrior unit : units) {
            if (unit instanceof Warlord w) {
                if (!isWarlordExists) {
                    this.warlord = w;
                    isWarlordExists = true;
                }else {
                    listToRemove.add(unit);
                }
            }
        }
        this.units.removeAll(listToRemove);
        return isWarlordExists;
    }

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

    public void moveUnits() {
        if (isWarlordExists()) {
            this.units = warlord.moveUnits(getUnits());
        }
    }
}
