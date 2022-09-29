package org.example.game;

import org.example.game.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmokeTestVampires {

    @Test
    @DisplayName("Vampire fighting other characters - smoke test")
    void vampire_smokeTest(){
        //given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();

        //when
        //then
        boolean res = Battle.fight(chuck, bruce);
        boolean res2 = Battle.fight(dave, carl);
        assertTrue(res);
        assertFalse(res2);
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        boolean res3 = Battle.fight(carl, mark);
        assertFalse(res3);
        assertFalse(carl.isAlive());
        boolean res4 = Battle.fight(bob, mike);
        assertFalse(res4);
        boolean res5 = Battle.fight(lancelot, rog);
        assertTrue(res5);
        boolean res6 = Battle.fight(eric, richard);
        assertFalse(res6);
        boolean res7 = Battle.fight(ogre, adam);
        assertTrue(res7);
    }

    @Test
    @DisplayName("1. Battle - Vampires vs Defenders vs Warriors")
    void battle_01_vampires_defenders_warriors() {
        //given
        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 4);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 2);

        //then
        //then
        boolean res1 = Battle.battle(myArmy, enemyArmy);
        boolean res2 = Battle.battle(army3, army4);
    }

}
