package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmokeTestDefenders {

    @Test
    @DisplayName("Defender fighting other characters - smoke test")
    void defender_smokeTest(){
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

        //when
        //then
        boolean res1 = Battle.fight(chuck, bruce);
        boolean res2 = Battle.fight(dave, carl);
        assertTrue(res1, "Warrior attacking first should win with other warrior");
        assertFalse(res2, "Knight should have won with warrior");
        assertTrue(chuck.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(bruce.isAlive());
        assertFalse(dave.isAlive());

        boolean res3 = Battle.fight(carl, mark);
        assertFalse(res3);
        assertFalse(carl.isAlive());

        boolean res4 = Battle.fight(bob, mike);
        assertFalse(res4, "Knight should defeat Defender");

        boolean res5 = Battle.fight(lancelot, rog);
        assertTrue(res5, "Defender should defeat Warrior");

    }

    @Test
    @DisplayName("1. Battle - Defenders vs Warriors")
    void battle_01_defenders_warriors() {
        //given
        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 1);

        var army4 = new Army();
        army4.addUnits(Warrior::new, 2);

        //when
        //then
        boolean res = Battle.battle(myArmy, enemyArmy);
        assertFalse(res);
        boolean res2 = Battle.battle(army3, army4);
        assertTrue(res2);
    }

    @Test
    @DisplayName("Rookie fighting Defender should not change its HP")
    void rookieVsDefender() {
        //given
        Rookie rookie = new Rookie();
        Defender defender = new Defender();

        //when
        boolean res = Battle.fight(rookie, defender);

        //then
        assertFalse(res);
        assertEquals(60, defender.getHP());
    }
}
