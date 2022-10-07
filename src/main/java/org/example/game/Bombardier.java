package org.example.game;

import java.util.Iterator;

public class Bombardier extends Warrior implements Bombard {

    private int bombingsAmount = 500;

    protected Bombardier() {
        super(30);
        setInitialHp(35);
    }

    /**
     * Special method for Bombardier so I added it via dedicated interface
     * After ammunition has ended break the while loop for searching opponents
     */

    @Override
    public void bombard(Warrior opponent) {
        if (isAlive() && bombingsAmount > 0) {
            this.hit(opponent);
            bombingsAmount--;
        }
    }

    @Override
    public void removeWeapons() {
        //can not steal from bombardier
    }

}
