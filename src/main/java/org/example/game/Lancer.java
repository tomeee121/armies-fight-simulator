package org.example.game;

public class Lancer extends Warrior implements KnowsDamageDealt {
    private double SECOND_OPPONENT_HIT_RATION = 50;

    protected Lancer() {
        super(6);
        setInitialHp(50);
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

    @Override
    void equipWeapon(Weapon weapon) {
        this.setInitialHp(getInitialHP() + weapon.getHp());
        setAttack(getAttack() + weapon.getAttack());
        addWeaponToList(weapon);
    }
}
