package org.example.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarlordTests {

    @Test
    @DisplayName("Test for warlord's moveUnits() method")
    void warlordSmokeTests() {
        var ronald = new Warlord();
        var heimdall = new Knight();

        assertFalse(Battle.fight(heimdall, ronald));

        var myArmy = new Army();
        myArmy.addUnits(Warlord::new, 1);
        myArmy.addUnits(Warrior::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 2);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warlord::new, 3);
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 2);
        enemyArmy.addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

//6, not 8, because only 1 Warlord per army could be

//        assertEquals(6, enemyArmy.size());

        boolean res = Battle.battle(myArmy, enemyArmy);
        assertTrue(res);
    }

//        @Test
//    void battle26() {
//
//        var army_1 = new Army();
//        army_1.addUnits(Warrior::new, 2);
//        army_1.addUnits(Lancer::new, 3);
//        army_1.addUnits(Defender::new, 1);
//        army_1.addUnits(Warlord::new, 1);
//        var army_2 = new Army();
//        army_2.addUnits(Warlord::new, 5);
//        army_2.addUnits(Vampire::new, 1);
//        army_2.addUnits(Rookie::new, 1);
//        army_2.addUnits(Knight::new, 1);
//
//        army_1.equipWarriorAtPosition(0, WeaponI.Sword());
//        army_2.equipWarriorAtPosition(0, WeaponI.Shield());
//
//        army_1.moveUnits();
//        army_2.moveUnits();
//
//        boolean res = Battle.battle(army_1, army_2);
//        assertFalse(res);
//    }

    @Test
    void battle01() {

        var army_1 = new Army();
        army_1.addUnits(Warrior::new, 1);
        var army_2 = new Army();
        army_2.addUnits(Warrior::new, 2);

        boolean res = Battle.battle(army_1, army_2);
        assertFalse(res);
    }

    @Test
    void battle07() {

        var army_1 = new Army();
        army_1.addUnits(Warrior::new, 5);
        army_1.addUnits(Defender::new, 4);
        army_1.addUnits(Defender::new, 5);
        var army_2 = new Army();
        army_2.addUnits(Warrior::new, 4);

        boolean res = Battle.battle(army_1, army_2);
        assertTrue(res);
    }

    @Test
    void battle03() {

        var army_1 = new Army();
        army_1.addUnits(Defender::new, 11);
        army_1.addUnits(Vampire::new, 3);
        army_1.addUnits(Warrior::new, 4);
        var army_2 = new Army();
        army_2.addUnits(Warrior::new, 4);
        army_2.addUnits(Defender::new, 4);
        army_2.addUnits(Vampire::new, 13);

        boolean res = Battle.battle(army_1, army_2);
        assertTrue(res);
    }

    @Test
    void battle17() {

        var army_1 = new Army();
        army_1.addUnits(Lancer::new, 7);
        army_1.addUnits(Vampire::new, 3);
        army_1.addUnits(Healer::new, 1);
        army_1.addUnits(Warrior::new, 4);
        army_1.addUnits(Healer::new, 1);
        army_1.addUnits(Defender::new, 2);
        var army_2 = new Army();
        army_2.addUnits(Warrior::new, 4);
        army_2.addUnits(Defender::new, 4);
        army_2.addUnits(Healer::new, 1);
        army_2.addUnits(Vampire::new, 6);
        army_2.addUnits(Lancer::new, 4);

        boolean res = Battle.battle(army_1, army_2);
        assertTrue(res);
    }


}
