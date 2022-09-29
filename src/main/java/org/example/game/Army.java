package org.example.game;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    public void removeDead() {
        var it = iterator();
        while (it.hasNext()) {
            if (!it.next().isAlive()) {
                it.remove();
            }
        }
    }

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

        public void healUnit(Warrior wounded) {

//            if(next != head) {
            if (warrior instanceof Healer healer) {
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
            addToTail(factory.get());
        }
        return this;
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
            if (prev == null){
                throw new IllegalStateException();
            }
            prev.next = cursor.next;
            cursor = prev;
            prev = null;
        }
    }

}