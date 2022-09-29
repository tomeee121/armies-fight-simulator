package org.example.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StraightFightTests {

    @Test
    @DisplayName("When fighting armies of warriors with same amount of soldiers but healers in defending army -- in straight fight wins attacking army")
    void firstBattleCheckIfHealerHasNoImpactOnResult() {

        //given
        var army_1 = new Army();
        army_1.addUnits(Warrior::new, 2);

        var army_2 = new Army();
        army_2.addUnits(Warrior::new, 1);
        army_2.addUnits(Healer::new, 1);
        army_2.addUnits(Warrior::new, 1);
        army_2.addUnits(Healer::new, 1);


        //when
        //then
        boolean res = Battle.straightFight(army_1, army_2);
        assertTrue(res);
    }

    @Test
    @DisplayName("Lancers with Healers loose with Warriors when would win in a battle")
    void lancersWithHealersLooseWithWarriorsWhenWouldWinInBattle() {

        //given
        var army_1 = new Army();
        army_1.addUnits(Warrior::new, 4);

        var army_2 = new Army();
        army_2.addUnits(Lancer::new, 1);
        army_2.addUnits(Healer::new, 1);
        army_2.addUnits(Lancer::new, 1);
        army_2.addUnits(Healer::new, 1);
        army_2.addUnits(Lancer::new, 1);
        army_2.addUnits(Healer::new, 1);
        army_2.addUnits(Warrior::new, 1);

        //when
        //then
        boolean res = Battle.straightFight(army_1, army_2);
        assertTrue(res);
    }
}
