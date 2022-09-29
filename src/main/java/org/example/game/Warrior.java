package org.example.game;

@FunctionalInterface
interface HasAttack {
    int getAttack();
    default void hit(CanReceiveDamage opponent) {
        opponent.receiveDamage(this);
        if (this instanceof WarriorInArmy warriorInArmy) {
            System.out.println("w instancji warrior in army");
            Warrior nextBehind = warriorInArmy.getNextBehind();
            nextBehind.heal((CanReceiveDamage) this);
        }
    }
}

@FunctionalInterface
interface HasHealth {
    int getHP();
    default boolean isAlive() {
        return getHP() > 0;
    };
}

interface CanReceiveDamage extends HasHealth {
    void receiveDamage(HasAttack damager);
    void setHP(int hp);
    default void heal(CanReceiveDamage allyWarrior){};
}

public class Warrior implements HasAttack, HasHealth, CanReceiveDamage, Cloneable {
    private int hp = 50;
    private int initialHp;
    private static final int ATTACK = 5;

    public void hit(CanReceiveDamage opponent) {
        opponent.receiveDamage(this);
    }

    @Override
    public void receiveDamage(HasAttack damager) {
        setHP(getHP() - damager.getAttack());
    }

    @Override
    protected Warrior clone() {
        try {
            return (Warrior) super.clone();     //shallow copy
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException catched");
        }
        return null;
    }

    protected Warrior() {
        this(50);
    }

    protected Warrior(int hp) {
        this.hp = initialHp = hp;
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void setHP(int hp) {
        this.hp = Math.min(hp, initialHp);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

}
