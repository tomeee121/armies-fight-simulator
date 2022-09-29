package org.example.game;

public class Healer extends Warrior {

    private static final int HEALING_POWER = 2;
    private static final int ATTACK = 0;
    private int allowedHealings = 100;

    protected Healer() {
        super(60);
    }

/**
    Very important not to let warrior with Healer behind and full Hp waste his given healings during attacking without being wounded
    as it may cause same armies battling ending up in defending army win
 */

    @Override
    public void heal(CanReceiveDamage allyWarrior) {
        if (allowedHealings > 0 && allyWarrior.getHP() < allyWarrior.getInitialHP()) {
            System.out.println("healing......... Being healed is " + allyWarrior.toString() + "   by   " + this.toString());
            allyWarrior.setHP(allyWarrior.getHP() + HEALING_POWER);

            decreaseAllowedHealings();
        }
    }

    /**
     If the attack is zero just do not waste memory on calculating attack with zero damage to make
     */

    @Override
    public void hit(CanReceiveDamage opponent) {
        // just do nothing
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    private void decreaseAllowedHealings() {
        if (allowedHealings > 0) {
            this.allowedHealings -= 1;
        }
    }
}
