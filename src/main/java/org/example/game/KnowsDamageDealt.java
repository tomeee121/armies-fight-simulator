package org.example.game;

public interface KnowsDamageDealt extends HasAttack {

    default int hitAndReportDamage(CanReceiveDamage opponent) {
        int hpBefore = opponent.getHP();
        HasAttack.super.hit(opponent);
        int hpAfter = opponent.getHP();
        int damageDealt = hpBefore - hpAfter;
        return damageDealt;
    }
}
