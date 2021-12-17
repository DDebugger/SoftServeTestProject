package army_battle.weapon;

public class Weapon {
    private final int health;
    private final int attack;
    private final int defence;
    private final int vampirism;
    private final int healPower;

    public Weapon(int health, int attack, int defence, int vampirism, int healPower) {
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.vampirism = vampirism;
        this.healPower = healPower;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getVampirism() {
        return vampirism;
    }

    public int getHealPower() {
        return healPower;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int health = 0;
        private int attack = 0;
        private int defence = 0;
        private int vampirism = 0;
        private int healPower = 0;

        public Builder health(int value) {
            this.health = value;
            return this;
        }

        public Builder attack(int value) {
            this.attack = value;
            return this;
        }

        public Builder defence(int value) {
            this.defence = value;
            return this;
        }

        public Builder vampirism(int value) {
            this.vampirism = value;
            return this;
        }

        public Builder healPower(int value) {
            this.healPower = value;
            return this;
        }

        public Weapon build() {
            return new Weapon(this.health, this.attack, this.defence, this.vampirism, this.healPower);
        }
    }
}
