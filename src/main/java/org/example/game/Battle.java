package org.example.game;

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
            army1.moveUnits();
            army2.moveUnits();

            passBombardOrder(army1, army2);

            /**
             Due to bomabardier dropping bombs on enemies position before fight need to check if enemy is alive after first wave
             */

            if (it1.hasNext() && it2.hasNext()) {
                army1.moveUnits();
                army2.moveUnits();

                fight(it1.next(), it2.next());
            }

        }
        return it1.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {

        while (true) {
            var it1 = army1.iterator();
            var it2 = army2.iterator();

            if (!it1.hasNext()) {
                return false;
            }
            if (!it2.hasNext()) {
                return true;
            }

            while (it1.hasNext() && it2.hasNext()) {
                army1.moveUnits();
                army2.moveUnits();
                fight(it1.next(), it2.next());
            }

            army1.removeDead();
            army2.removeDead();
        }
    }

/**
    Using two methods to provide compliance with chain of responsibility pattern - first is searching for bombardiers in army
    then delegate to method executing bombing on enemy army by Bombardier passed to it
 */

    static void passBombardOrder(Army army1, Army army2) {
        var command = new Command();
        var bombardiersArmy1 = command.getBombardiers(army1);
        var bombardiersArmy2 = command.getBombardiers(army2);

/**
        Pass every bombardier from first army to command executing bombings on second army
*/

        for (Bombardier bombardier : bombardiersArmy1) {
            var commandWithBombardier = new Command(bombardier);
            commandWithBombardier.bombard(army2);
        }

        for (Bombardier bombardier : bombardiersArmy2) {
            var commandWithBombardier = new Command(bombardier);
            commandWithBombardier.bombard(army1);
        }
    }

}
