package org.example.game;

import org.example.game.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LancerTests {

    @Test
    void smokeTestLancer() {
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

        boolean res = Battle.fight(chuck, bruce);
        assertTrue(res);
        boolean res2 = Battle.fight(dave, carl);
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

        boolean res8 = Battle.fight(freelancer, vampire);
        assertTrue(res8);
        assertTrue(freelancer.isAlive());

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 4);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 2);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Defender::new, 2);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Lancer::new, 2);

        boolean res9 = Battle.battle(myArmy, enemyArmy);
        assertTrue(res9);

        boolean res10 = Battle.battle(army3, army4);
        assertFalse(res10);
    }

    @Test
    void defender_warrior_vampire_lancer02() {
        //given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Lancer::new, 5);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 5);

        //when
        //then
        boolean res = Battle.battle(army1, army2);
        assertFalse(res);
    }

    @Test
    void defender_warrior_vampire_lancer03() {
        //given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Lancer::new, 5);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 5);

        //when
        //then
        boolean res = Battle.battle(army1, army2);
        assertFalse(res);
    }

    @Test
    void defender_warrior_vampire_lancer04() {
        //given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Lancer::new, 7);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 4);

        //when
        //then
        boolean res = Battle.battle(army1, army2);
        assertTrue(res);
    }

    @Test
    void warrior__lancer05() {
        //given
        Army armyWarriors = new Army();
        Army armyWarriorsWithLancer = new Army();
        armyWarriors.addUnits(Warrior::new, 2);
        armyWarriorsWithLancer.addUnits(Lancer::new, 1);
        armyWarriorsWithLancer.addUnits(Warrior::new, 1);

        //when
        //then
        boolean res = Battle.battle(armyWarriors, armyWarriorsWithLancer);
        assertFalse(res);
    }
}
