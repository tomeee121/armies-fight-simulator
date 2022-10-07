package org.example.game;

import java.util.Iterator;

public class Command implements CommandI {

    private Bombardier bombardier;


    protected Command(Bombardier bombardier) {
        this.bombardier = bombardier;
    }

    protected Command() {}

    @Override
    public void bombard(Army army) {
        var iterator = army.iterator();
        while(iterator.hasNext()) {
            this.bombardier.bombard(iterator.next());
        }
    }
}
