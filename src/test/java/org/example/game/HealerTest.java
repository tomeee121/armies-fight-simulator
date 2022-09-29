package org.example.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealerTest {
    @Test
    void healerSmokeTest() {
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
        var freelancer = new Lancer();
        var vampire = new Vampire();
        var priest = new Healer();

        boolean res1 = Battle.fight(chuck, bruce);
        assertTrue(res1);
        boolean res2 = Battle.fight(dave, carl);
        assertFalse(res2);
        boolean res3 = chuck.isAlive();
        assertTrue(res3);
        boolean res4 = bruce.isAlive();
        assertFalse(res4);
        boolean res5 = carl.isAlive();
        assertTrue(res5);
        boolean res6 = dave.isAlive();
        assertFalse(res6);
        boolean res7 = Battle.fight(carl, mark);
        assertFalse(res7);
        boolean alive = carl.isAlive();
        assertFalse(alive);
        boolean res8 = Battle.fight(bob, mike);
        assertFalse(res8);
        boolean res9 = Battle.fight(lancelot, rog);
        assertTrue(res9);
        boolean res10 = Battle.fight(eric, richard);
        assertFalse(res10);
        boolean res11 = Battle.fight(ogre, adam);
        assertTrue(res11);
        boolean res12 = Battle.fight(freelancer, vampire);
        assertTrue(res12);
        boolean alive1 = freelancer.isAlive();
        assertTrue(alive1);
        assertTrue(freelancer.getHP() == 14);
        priest.heal(freelancer);
        assertTrue(freelancer.getHP() == 16);

        var my_army = new Army();
        my_army.addUnits(Defender::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Vampire::new, 2);
        my_army.addUnits(Lancer::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Warrior::new, 1);

        var enemy_army = new Army();
        enemy_army.addUnits(Warrior::new, 2);
        enemy_army.addUnits(Lancer::new, 4);
        enemy_army.addUnits(Healer::new, 1);
        enemy_army.addUnits(Defender::new, 2);
        enemy_army.addUnits(Vampire::new, 3);
        enemy_army.addUnits(Healer::new, 1);

        var army_3 = new Army();
        army_3.addUnits(Warrior::new, 1);
        army_3.addUnits(Lancer::new, 1);
        army_3.addUnits(Healer::new, 1);
        army_3.addUnits(Defender::new, 2);

        var army_4 = new Army();
        army_4.addUnits(Vampire::new, 3);
        army_4.addUnits(Warrior::new, 1);
        army_4.addUnits(Healer::new, 1);
        army_4.addUnits(Lancer::new, 2);

        boolean fight1 = Battle.battle(my_army, enemy_army);
        assertFalse(fight1);
        boolean fight2 = Battle.battle(army_3, army_4);

        assertTrue(fight2);
    }

/**
 special case -> description in Healer class
*/

    @Test
    void healerAndDefenderPotentialInfiniteBattle(){

        //given
        var army_1 = new Army();
        army_1.addUnits(Defender::new, 1);
        army_1.addUnits(Healer::new, 1);

        var army_2 = new Army();
        army_2.addUnits(Defender::new, 1);
        army_2.addUnits(Healer::new, 1);

        //when
        //then
        boolean res = Battle.battle(army_1, army_2);

        assertTrue(res);
        assertEquals(army_1.firstAlive().next().getHP(), 1);
    }
}
