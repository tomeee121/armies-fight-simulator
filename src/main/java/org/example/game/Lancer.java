package org.example.game;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Lancer extends Warrior implements KnowsDamageDealt {
    private int attack = 6;
    private double SECOND_OPPONENT_HIT_RATION = 50;


    @Override
    public void hit(CanReceiveDamage opponent) {
        int damageDealt = hitAndReportDamage(opponent);
        if (opponent instanceof WarriorInArmy unitInArmy) {
            var theSecond = unitInArmy.getNextBehind();
            if (theSecond != null) {
                int damageToTheSecond = (int) (damageDealt * SECOND_OPPONENT_HIT_RATION / 100);
                theSecond.receiveDamage(() -> damageToTheSecond);
            }
        }
    }

    @Override
    void equipWeapon(Weapon weapon) {
        this.setHP(getHP() + weapon.getHp());
        this.attack = getAttack() + weapon.getAttack();
        addWeaponToList(weapon);
    }

    @Override
    public int getAttack() {
        return attack;
    }

}
