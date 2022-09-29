package org.example.game;

public class Defender extends Warrior {
    private static final int ATTACK = 3;
    private static final int DEFENSE = 2;

    protected Defender() {
        super(60);
    }

    @Override
    public void receiveDamage(HasAttack damager) {
        int finalDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> finalDamage);
    }

    public int getAttack() {
        return ATTACK;
    }

    int getDefense() {
        return DEFENSE;
    }

}
