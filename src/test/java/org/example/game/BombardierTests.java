package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BombardierTests {

    @Test
    @DisplayName("Bombardier - game-changer for army2 to turn it around")
    void smokeTest() {

        //given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 10);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Defender::new, 2);

        army2.addUnits(Warrior::new, 1);
        army2.addUnits(Vampire::new, 1);
        army2.addUnits(Defender::new, 2);
        army2.addUnits(Bombardier::new, 1);

        //when
        //then
        boolean res = Battle.battle(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("Bombardier - true power:)) - game-changer 2")
    void smokeTest2() {

        //given
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Lancer::new, 10);
        army1.addUnits(Vampire::new, 1);
        army1.addUnits(Defender::new, 1);
        army1.addUnits(Bombardier::new, 17);

        army2.addUnits(Warrior::new, 10);
        army2.addUnits(Vampire::new, 500);
        army2.addUnits(Defender::new, 111);

        //when
        //then
        boolean res = Battle.battle(army1, army2);
        assertTrue(res);
    }
}
