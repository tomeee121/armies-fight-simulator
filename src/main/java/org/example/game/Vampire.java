package org.example.game;

public class Vampire extends Warrior implements KnowsDamageDealt{

    private static final int VAMPIRISM = 50;
    private static final int ATTACK = 4;

    protected Vampire() {
        super(40);
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        int damageDealt = hitAndReportDamage(opponent);
        final int percent = 100;
        int healMyselfBy = damageDealt * VAMPIRISM / percent;
        setHP(getHP() + healMyselfBy);
    }

    @Override
    public int getAttack() {
        return this.ATTACK;
    }
}
