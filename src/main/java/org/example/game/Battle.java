package org.example.game;

import java.util.Iterator;

public class Battle {

    public static Integer numberOfRounds = 0;


    public static boolean fight(Warrior attacker, Warrior defender) {
        do {
            numberOfRounds++;
            attacker.hit(defender);
            if (defender.isAlive()) {
                defender.hit(attacker);
            }

        } while (attacker.isAlive() && defender.isAlive());

        return (attacker.isAlive());
    }

    public static boolean battle(Army army1, Army army2) {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

}
