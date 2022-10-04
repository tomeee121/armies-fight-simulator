package org.example.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

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

    }

    private Node head = new Node(null);
    private Node tail = head;
    private Warlord warlord = null;

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
                if (warlord == null) {
                    addToTail(supply);
                    warlord = (Warlord) supply;
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

        if (warlord != null) {
            System.out.println("w move units");
            Iterable<Warrior> arrangedOrder = warlord.arrangePositions(iterator());
    /**
    Now head can be reseted and we again add nodes from it (but based on an ordered list)
    */
            removeAllNodes();

            for (Warrior arrangedWarrior : arrangedOrder) {
                addToTail(arrangedWarrior);
            }
        }
    }

    private void removeAllNodes() {
        head = new Node(null);
        tail = head;
    }

    boolean hasWarlord() {
        return warlord != null;
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
