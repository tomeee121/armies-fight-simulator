package org.example.game;

public class Lancer extends Warrior implements KnowsDamageDealt {
    private static final int ATTACK = 6;
    private static final double SECOND_OPPONENT_HIT_RATION = 50;


    @Override
    public int getAttack() {
        return ATTACK;
    }

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

}
