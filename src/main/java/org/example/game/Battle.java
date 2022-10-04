package org.example.game;

public class Battle {

    public static Integer numberOfRounds = 0;


    public static boolean fight(Warrior attacker, Warrior defender) {
        do {
            numberOfRounds++;
            attacker.hit(defender);
            System.out.println(attacker);
            if (defender.isAlive()) {
                defender.hit(attacker);
            }

        } while (attacker.isAlive() && defender.isAlive());

        return (attacker.isAlive());
    }

    public static boolean battle(Army army1, Army army2) {
        Iterable<Bombardier> bombardiers1 = army1.getBombardiers();
        Iterable<Bombardier> bombardiers2 = army2.getBombardiers();

        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while (it1.hasNext() && it2.hasNext()) {
            bombardiers1.forEach(bombardier -> bombardier.bombard(it2));
            bombardiers2.forEach(bombardier -> bombardier.bombard(it1));

            army1.moveUnits();
            army2.moveUnits();

            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {

        while (true) {
            var it1 = army1.iterator();
            var it2 = army2.iterator();

            if(!it1.hasNext()) { return false; }
            if(!it2.hasNext()) { return true; }

            while (it1.hasNext() && it2.hasNext()) {
                army1.moveUnits();
                army2.moveUnits();
                fight(it1.next(), it2.next());
            }

            army1.removeDead();
            army2.removeDead();
        }
    }

}
