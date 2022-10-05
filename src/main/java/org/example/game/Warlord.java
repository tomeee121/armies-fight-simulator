package org.example.game;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Warlord extends Warrior {

    private int defense = 2;

    protected Warlord() {
        super(4);
        setInitialHp(100);
    }

    Iterable<Warrior> arrangePositions(Iterator<Warrior> iterator) {

        var helpers = new HelpersForSelectingPosition();

        List<Warrior> sortedWarriors = new LinkedList<>();
        List<Warrior> unSortedWarriors = new LinkedList<>();

        while (iterator.hasNext()) {
            unSortedWarriors.add(iterator.next());
        }


        List<Warrior> firstPosition = helpers.canAddFirstLancer(unSortedWarriors);
        for (Warrior warriorToRemove : firstPosition) {
            unSortedWarriors.removeIf(o -> o.equals(warriorToRemove));
        }


        List<Warrior> healersFromSecond = helpers.canAddHealersFromSecondPlace(unSortedWarriors);
        for (Warrior warriorToRemove : healersFromSecond) {
            unSortedWarriors.removeIf(o -> o.equals(warriorToRemove));
        }

        List<Warrior> rest1 = helpers.canAddLancersInFrontIfThereIsNoHealer(unSortedWarriors);
        for (Warrior warriorToRemove : rest1) {
            unSortedWarriors.removeIf(o -> o.equals(warriorToRemove));
        }

        List<Warrior> rest2 = helpers.canAddAllTheRest(unSortedWarriors);

        Stream.of(firstPosition, healersFromSecond, rest1, rest2)
              .flatMap(Collection::stream)
              .peek(System.out::println)
              .forEach(sortedWarriors::add); //flatMap to add elem. from a few lists

        return sortedWarriors;

    }

    /**
     * Methods in the below class are helpers to find desired unit e.g. Lancer to fulfill first position of army, then Healers etc.
     * They are invoked by moveUnits() method at the start of every battle's round
     */

    private class HelpersForSelectingPosition {
        private List<Warrior> canAddFirstLancer(List<Warrior> unSortedWarriors) {
            List<Warrior> canAddFirstLancerList = new LinkedList<>();
            var it = unSortedWarriors.iterator();

            boolean hasFoundLancer = false;

            while (it.hasNext()) {
                Warrior warrior = it.next();
                if (warrior instanceof Lancer) {
                    canAddFirstLancerList.add(warrior);
                    hasFoundLancer = true;
                    return canAddFirstLancerList;
                }
            }

            /**
             Important to create new instance of iterator since there is need to check all with different condition as it would start from last element other way
             */
            if (!hasFoundLancer) {
                var it2 = unSortedWarriors.iterator();
                while (it2.hasNext()) {
                    Warrior warrior2 = it2.next();
                    if (!(warrior2 instanceof Warlord || warrior2 instanceof Healer)) {
                        canAddFirstLancerList.add(warrior2);
                        return canAddFirstLancerList;
                    }
                }

            }
            return canAddFirstLancerList;
        }

        private List<Warrior> canAddHealersFromSecondPlace(List<Warrior> unSortedWarriors) {
            List<Warrior> canAddHealersFromSecondPlaceList = new LinkedList<Warrior>();
            var it = unSortedWarriors.iterator();

            while (it.hasNext()) {
                Warrior next = it.next();
                if (next instanceof Healer) {
                    canAddHealersFromSecondPlaceList.add(next);
                }
            }
            return canAddHealersFromSecondPlaceList;
        }

        private List<Warrior> canAddLancersInFrontIfThereIsNoHealer(List<Warrior> unSortedWarriors) {
            List<Warrior> canAddLancersInFrontIfThereIsNoHealerList = new LinkedList<Warrior>();
            var it = unSortedWarriors.iterator();

            while (it.hasNext()) {
                Warrior next = it.next();
                if (next instanceof Lancer) {
                    canAddLancersInFrontIfThereIsNoHealerList.add(next);
                }
            }
            return canAddLancersInFrontIfThereIsNoHealerList;
        }

        private List<Warrior> canAddAllTheRest(List<Warrior> unSortedWarriors) {
            List<Warrior> canAddAllTheRestList = new LinkedList<Warrior>();
            var it = unSortedWarriors.iterator();

            while (it.hasNext()) {
                Warrior next = it.next();
                if (!(next instanceof Warlord)) {
                    canAddAllTheRestList.add(next);
                }
            }
            var it2 = unSortedWarriors.iterator();

            while (it2.hasNext()) {
                Warrior next = it2.next();
                if (next instanceof Warlord) {
                    canAddAllTheRestList.add(next);
                }
            }

            return canAddAllTheRestList;
        }
    }


    @Override
    public void receiveDamage(HasAttack damager) {
        int finalDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> finalDamage);
    }

    @Override
    void equipWeapon(Weapon weapon) {
        this.setInitialHp(getInitialHP() + weapon.getHp());
        this.setAttack(getAttack() + weapon.getAttack());
        this.defense = getDefense() + weapon.getDefence();
        addWeaponToList(weapon);
    }

    int getDefense() {
        return defense;
    }
}
