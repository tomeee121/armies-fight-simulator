package org.example.game;

import java.util.Iterator;

public class Bombardier extends Warrior implements Bombard {

    protected Bombardier() {
        super(30);
        setInitialHp(35);
    }

    @Override
    public void bombard(Iterator<Warrior> iterator) {
        while (iterator.hasNext()) {
            iterator.next().setHP(getHP() + this.getAttack());
        }
    }

}
