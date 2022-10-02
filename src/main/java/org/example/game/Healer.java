package org.example.game;

public class Healer extends Warrior {

    private int healingPower = 2;
    private int attack = 0;
    private int allowedHealings = 100;

    protected Healer() {
        setInitialHp(60);
    }


    /**
    Very important not to let warrior with Healer behind and full Hp waste his given healings during attacking without being wounded
    as it may cause same armies battling ending up in defending army win

    Without if statement :
    'allyWarrior.getHP() < allyWarrior.getInitialHP()'
    the last test from HealerTests class is not green
 */

    @Override
    public void heal(CanReceiveDamage allyWarrior) {
            if (allowedHealings > 0 && allyWarrior.getHP() < allyWarrior.getInitialHP()) {
                System.out.println("healing......... Being healed is " + allyWarrior.toString() + "   by   " + this.toString());
                allyWarrior.setHP(allyWarrior.getHP() + getHealingPower());

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
    void equipWeapon(Weapon weapon) {
        this.setInitialHp(getInitialHP() + weapon.getHp());
        this.attack = getAttack() + weapon.getAttack();
        this.healingPower = getHealingPower() + weapon.getHealPower();
        addWeaponToList(weapon);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    private void decreaseAllowedHealings() {
        if (allowedHealings > 0) {
            this.allowedHealings -= 1;
        }
    }

    int getHealingPower() {
        return healingPower;
    }

}
