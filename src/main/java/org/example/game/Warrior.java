package org.example.game;

import java.util.LinkedList;
import java.util.List;

@FunctionalInterface
interface HasAttack {
    int getAttack();
    default void hit(CanReceiveDamage opponent) {
        opponent.receiveDamage(this);
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
    int getInitialHP();

    default void heal(CanReceiveDamage allyWarrior){};
}

public class Warrior implements HasAttack, HasHealth, CanReceiveDamage, Cloneable {
    private int hp;
    private int initialHp;
    private int attack = 5;
    private List<Weapon> weaponsAvailable = new LinkedList<>();

    void equipWeapon(Weapon weapon) {
        hp = initialHp = getHP() + weapon.getHp();
        attack = getAttack() + weapon.getAttack();
        addWeaponToList(weapon);
    }

    void addWeaponToList(Weapon weapon) {
        weaponsAvailable.add(weapon);
    }

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

/**
    No change to initial HP or attack after Warrior is equipped with weapons
    Follow open-close principle
 */

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void setHP(int hp) {
        this.hp = Math.min(initialHp, hp);
    }

    @Override
    public int getAttack() {
        return attack;
    }


    @Override
    public int getInitialHP() {
        return initialHp;
    }

}
