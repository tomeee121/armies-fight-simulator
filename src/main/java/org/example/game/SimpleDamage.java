package org.example.game;

import java.util.Objects;

public class SimpleDamage implements Damage{

    private Warrior damageDealer;
    private int value;

    protected SimpleDamage(Warrior damageDealer, int value) {
        this.damageDealer = Objects.requireNonNull(damageDealer);
        this.value = value;
    }

    @Override
    public Warrior getDamageDealer() {
        return damageDealer;
    }

    @Override
    public int getValue() {
        return value;
    }
}
