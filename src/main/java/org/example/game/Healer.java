package org.example.game;

public class Healer extends Warrior {

    private static final int HEALING_POWER = 2;
    private static final int ATTACK = 0;

    protected Healer() {
        super(60);
    }

    @Override
    public void heal(CanReceiveDamage allyWarrior){
        System.out.println("healing......... Being healed is "+allyWarrior.toString()+"   by   "+this.toString());
        allyWarrior.setHP(allyWarrior.getHP() + HEALING_POWER);
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        // just do nothing
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
