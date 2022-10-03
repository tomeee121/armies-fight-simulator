package org.example.game;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Army implements Iterable<Warrior> {

    private class Node
            extends Warrior
            implements WarriorInArmy {

        Warrior warrior;
        Node next;

        Node(Warrior warrior) {
            this.warrior = warrior;
            this.next = this;
        }

        @Override
        public Warrior getNextBehind() {
            return next == head ? null : next;
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHP() {
            return warrior.getHP();
        }

        @Override
        public void setHP(int hp) {
            warrior.setHP(hp);
        }

        @Override
        public void receiveDamage(HasAttack damager) {
            warrior.receiveDamage(damager);
        }

        @Override
        public void hit(CanReceiveDamage opponent) {
            warrior.hit(opponent);
            next.healUnit(warrior);
        }

        /**
         * Healing referes to WarriorInArmy type object not single so implementation of base healing method was added in a place where nextBehind can be easily determined
         * warrior object in healUnit() method is nextBehind because on this object the method is invoked
         * Then pass it till object is equal head (no next Warrior)
         */
        public void healUnit(Warrior wounded) {

            if (warrior instanceof Healer healer && wounded != null) {
                healer.heal(wounded);
            }
            if (next != head) {
                next.healUnit(warrior);
            }
        }

        @Override
        protected Warrior clone() {
            return warrior.clone();
        }

        private Node() {
        }
    }

    private Node head = new Node(null);
    private Node tail = head;
    private boolean hasWarlord = false;

    private Warrior peek() {
        return head.next;
    }

    boolean isEmpty() {
        return tail == head;
    }

    private void removeFromHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (tail == head.next) {
            tail = head;
        }
        head.next = head.next.next;

    }

    private void addToTail(Warrior warrior) {
        var node = new Node(warrior);
        node.next = head;
        tail.next = node;
        tail = node;
    }

    public Army addUnits(Supplier<Warrior> factory, int amountToCreate) {
        for (int i = 0; i < amountToCreate; i++) {
            var supply = factory.get();

            if (supply instanceof Warlord) {
                if (!hasWarlord) {
                    addToTail(supply);
                    hasWarlord = true;
                    continue;
                } else {
                    continue;
                }
            } else {
                addToTail(supply);
            }
        }
        return this;
    }

    void removeDead() {
        var it = iterator();
        while (it.hasNext()) {
            if (!it.next()
                   .isAlive()) {
                it.remove();
            }
        }
    }

    /**
     * Move iterator to given position, pass weapon to add to given type of Warrior
     * return Army for chaining
     */

    Army equipWarriorAtPosition(int position, Weapon weapon) {
        Iterator<Warrior> equipIterator = iterator();
        Warrior warriorToEquip = null;

        for (int i = 0; i <= position; i++) {
            warriorToEquip = equipIterator.next();
        }

        warriorToEquip.equipWeapon(weapon);
        return this;
    }


    void moveUnits() {
        var helpers = new HelpersForSelectingPosition();

        if (hasWarlord) {

            List<Warrior> unSortedWarriors = new LinkedList<>();

            var iterator = iterator();
            while (iterator.hasNext()) {
                unSortedWarriors.add(iterator.next());
            }

            System.out.println("nie SORTED:");
            for (Warrior unSortedWarrior : unSortedWarriors) {
                System.out.println(unSortedWarrior + "<--- z unsorded list");
            }
            System.out.println(unSortedWarriors.size());
            System.out.println("koniec NIE SORTED:");

            List<Warrior> sortedWarriors = new LinkedList<>();

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
                  .forEach(sortedWarriors::add); //flatMap to add elem. from a few lists


/**
 Now head can be reseted and we again add nodes from it (but based on an ordered list)
 */

            head = new Node(null);
            tail = head;

            for (Warrior sortedWarrior : sortedWarriors) {
                System.out.println("sorted WARRIORS" + sortedWarrior);
                addToTail(sortedWarrior);
            }


        }
    }

    /**
     * Methods in the below class are helpers to find desired unit e.g. Lancer to fulfill first position of army, then Healers etc.
     * They are invoked by moveUnits() method at the start of every battle's round
     */

    private class HelpersForSelectingPosition {
        private List<Warrior> canAddFirstLancer(List<Warrior> unSortedWarriors) {
            List<Warrior> canAddFirstLancerList = new LinkedList<Warrior>();
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
            if (hasWarlord()) {
                while (it.hasNext()) {
                    Warrior next = it.next();
                    if (next instanceof Warlord) {
                        canAddAllTheRestList.add(next);
                    }
                }
            }
            return canAddAllTheRestList;
        }
    }

    boolean hasWarlord() {
        return hasWarlord;
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new SimpleIterator();
    }

    public Iterator<Warrior> firstAlive() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<Warrior> {
        @Override
        public boolean hasNext() {
            while (!isEmpty() && !peek().isAlive()) {
                removeFromHead();
            }
            return !isEmpty();
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            var res = peek();
            return res == head ? null : res;
        }
    }

    private class SimpleIterator implements Iterator<Warrior> {
        Node cursor = head;
        Node prev = null;

        @Override
        public boolean hasNext() {
            return cursor.next != head;
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prev = cursor;
            cursor = cursor.next;
            return cursor.warrior;
        }

        @Override
        public void remove() {
            if (prev == null) {
                throw new IllegalStateException();
            }
            prev.next = cursor.next;
            cursor = prev;
            prev = null;
        }

    }

    protected int size() {
        int size = 0;
        var it = iterator();
        while (it.hasNext()) {
            Warrior next = it.next();
            size++;
        }
        return size;
    }

}
