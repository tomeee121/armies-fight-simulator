package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.game.WeaponI.*;
import static org.junit.jupiter.api.Assertions.*;

public class EquipedWarriorsBattleTests {

    @Test
    @DisplayName("Simple test for battle without equipping warrior")
    void battleSmokeTest() {

        //given
        var army_1 = new Army();
        army_1.addUnits(Defender::new, 1);

        var army_2 = new Army();
        army_2.addUnits(Defender::new, 1);

        //when
        //then
        boolean res = Battle.battle(army_1, army_2);

        assertTrue(res);
    }

    @Test
    @DisplayName("Simple test for battle resulting in different result after equipping warrior")
    void battleSmokeTest2() {

        //given
        var army_1 = new Army();
        army_1.addUnits(Defender::new, 1);

        var army_2 = new Army();
        army_2.addUnits(Defender::new, 1);
        army_2.equipWarriorAtPosition(0, MagicWand());

        //when
        //then
        boolean res = Battle.battle(army_1, army_2);

        assertFalse(res);
    }

    @Test
    @DisplayName("Simple test for fight without equipping warrior")
    void fightSmokeTest() {

        //given
        var knight = new Knight();

        var warrior = new Warrior();

        //when
        //then
        boolean res = Battle.fight(knight, warrior);

        assertTrue(res);
    }

    @Test
    @DisplayName("Simple test for fight resulting in different result after equipping warrior")
    void fightSmokeTest2() {

        //given
        var knight = new Knight();

        var warrior = new Warrior();
        warrior.addWeaponToEquipment(MagicWand());
        warrior.addWeaponToEquipment(GreatAxe());
        warrior.addWeaponToEquipment(Sword());

        //when
        //then
        boolean res = Battle.fight(knight, warrior);

        assertFalse(res);
    }

    /**
     *
     * Another part of last test from BattleTest class after equipping attacking Warriors
     *
     */

    @ParameterizedTest
    @MethodSource("provideArmies")
    @DisplayName("Battle - Warriors with equipment should win attacking unequipped Knights")
    void battle_05(Army firstArmy, Army secondArmy) {

        //when
        boolean resOfBattle = Battle.battle(firstArmy, secondArmy);

        //then
        assertAll(
                () -> assertTrue(resOfBattle, "Army of Knights should have not successfully defended"),
                () -> assertEquals(resOfBattle, true, "Army of Knights should have not successfully defended - nr 2")

        );
    }

    public static Stream<Arguments> provideArmies() {

        return Stream.of(
                Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 12)
                                  .equipWarriorAtPosition(0, MagicWand())
                                  .equipWarriorAtPosition(2, Sword())
                                  .equipWarriorAtPosition(3, Sword())
                                  .equipWarriorAtPosition(4, GreatAxe())
                                  .equipWarriorAtPosition(5, Sword())
                                  .equipWarriorAtPosition(6, Shield()),
                        new Army().addUnits(() -> new Knight(), 11),
                        true),
                Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 61)
                                  .equipWarriorAtPosition(0, MagicWand())
                                  .equipWarriorAtPosition(2, Sword())
                                  .equipWarriorAtPosition(3, Sword())
                                  .equipWarriorAtPosition(4, GreatAxe())
                                  .equipWarriorAtPosition(5, Sword())
                                  .equipWarriorAtPosition(6, Shield())
                                  .equipWarriorAtPosition(7, MagicWand())
                                  .equipWarriorAtPosition(8, Sword())
                                  .equipWarriorAtPosition(9, Sword())
                                  .equipWarriorAtPosition(10, GreatAxe())
                                  .equipWarriorAtPosition(11, Sword())
                                  .equipWarriorAtPosition(12, Shield())
                                  .equipWarriorAtPosition(14, MagicWand())
                                  .equipWarriorAtPosition(15, MagicWand())
                                  .equipWarriorAtPosition(20, MagicWand())
                                  .equipWarriorAtPosition(21, MagicWand())
                                  .equipWarriorAtPosition(22, MagicWand())
                                  .equipWarriorAtPosition(23, MagicWand())
                                  .equipWarriorAtPosition(24, MagicWand())
                                  .equipWarriorAtPosition(25, MagicWand())
                                  .equipWarriorAtPosition(26, MagicWand())
                        ,
                        new Army().addUnits(() -> new Knight(), 60),
                        true),
                Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 210)
                                  .equipWarriorAtPosition(0, MagicWand())
                                  .equipWarriorAtPosition(2, Sword())
                                  .equipWarriorAtPosition(3, Sword())
                                  .equipWarriorAtPosition(4, GreatAxe())
                                  .equipWarriorAtPosition(5, Sword())
                                  .equipWarriorAtPosition(6, Shield())
                                  .equipWarriorAtPosition(7, MagicWand())
                                  .equipWarriorAtPosition(8, Sword())
                                  .equipWarriorAtPosition(9, Sword())
                                  .equipWarriorAtPosition(11, GreatAxe())
                                  .equipWarriorAtPosition(12, Sword())
                                  .equipWarriorAtPosition(13, Shield())
                                  .equipWarriorAtPosition(21, MagicWand())
                                  .equipWarriorAtPosition(22, Sword())
                                  .equipWarriorAtPosition(31, Sword())
                                  .equipWarriorAtPosition(42, GreatAxe())
                                  .equipWarriorAtPosition(52, Sword())
                                  .equipWarriorAtPosition(61, Shield())
                                  .equipWarriorAtPosition(71, MagicWand())
                                  .equipWarriorAtPosition(81, Sword())
                                  .equipWarriorAtPosition(91, Sword())
                                  .equipWarriorAtPosition(111, GreatAxe())
                                  .equipWarriorAtPosition(121, Sword())
                                  .equipWarriorAtPosition(131, Shield())
                                  .equipWarriorAtPosition(140, MagicWand())
                                  .equipWarriorAtPosition(141, MagicWand())
                                  .equipWarriorAtPosition(142, MagicWand())
                                  .equipWarriorAtPosition(143, MagicWand())
                                  .equipWarriorAtPosition(144, MagicWand())
                                  .equipWarriorAtPosition(145, MagicWand())
                                  .equipWarriorAtPosition(146, MagicWand())
                                  .equipWarriorAtPosition(147, MagicWand())
                                  .equipWarriorAtPosition(148, MagicWand())
                                  .equipWarriorAtPosition(149, MagicWand())
                                  .equipWarriorAtPosition(150, MagicWand())
                                  .equipWarriorAtPosition(151, MagicWand())
                                  .equipWarriorAtPosition(152, MagicWand())
                                  .equipWarriorAtPosition(153, MagicWand())
                                  .equipWarriorAtPosition(154, MagicWand())
                                  .equipWarriorAtPosition(155, MagicWand())
                                  .equipWarriorAtPosition(156, MagicWand())
                                  .equipWarriorAtPosition(157, MagicWand())
                                  .equipWarriorAtPosition(158, MagicWand())
                                  .equipWarriorAtPosition(159, MagicWand())
                                  .equipWarriorAtPosition(160, MagicWand())
                                  .equipWarriorAtPosition(161, MagicWand())
                                  .equipWarriorAtPosition(162, MagicWand())
                                  .equipWarriorAtPosition(163, MagicWand())
                                  .equipWarriorAtPosition(164, MagicWand())
                                  .equipWarriorAtPosition(165, MagicWand())
                                  .equipWarriorAtPosition(166, MagicWand()),
                        new Army().addUnits(() -> new Knight(), 208),
                        true));
    }
}
