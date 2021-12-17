package army_battle.unit;

import army_battle.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Warlord extends Warrior implements DefenceCapable {
    private static final int INIT_HEALTH = 100;
    private int defence = 2;

    public Warlord() {
        super(INIT_HEALTH);
    }

    public List<Warrior> moveUnits(List<Warrior> units) {

        List<Warrior> lancers = units.stream().filter(Lancer.class::isInstance)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Warrior> healers = units.stream().filter(Healer.class::isInstance)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Warrior> warriors = units.stream().filter(unit ->
                        unit != null
                        && !(unit instanceof Lancer)
                        && !(unit instanceof Healer)
                        && !(unit instanceof Warlord))
                .collect(Collectors.toCollection(ArrayList::new));

        List<Warrior> warlords = units.stream().filter(Warlord.class::isInstance)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Warrior> unitsCopy = new ArrayList<>();
        if (!lancers.isEmpty()) {
            unitsCopy.add(lancers.get(0));
            lancers.remove(0);

            unitsCopy.addAll(healers);
            unitsCopy.addAll(lancers);
        } else {
            if (!warriors.isEmpty()) {
                unitsCopy.add(warriors.get(0));
                warriors.remove(0);
            }

            unitsCopy.addAll(healers);
        }
        unitsCopy.addAll(warriors);
        unitsCopy.addAll(warlords);

        return unitsCopy;

    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        this.defence = getDefence() + weapon.getDefence();

    }


    @Override
    protected int getInitAttack() {
        return 4;
    }

    @Override
    public void getDamageFrom(AttackCapable warrior) {
        setHealth(getHealth() - Math.max(0, warrior.getAttack() - getDefence()));
    }

    @Override
    public int getDefence() {
        return defence;
    }
}
