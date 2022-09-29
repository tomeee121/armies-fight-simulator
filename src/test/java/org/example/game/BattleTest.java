package org.example.game;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {

    @Test
    void smokeTest() {
        //arrange given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        //act when
        var res1 = Battle.fight(chuck, bruce);
        var res2 = Battle.fight(dave, carl);

        //assert then
        assertTrue(res1);
        assertFalse(res2);
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    @DisplayName("1. Fight - Warrior vs Knight")
    void fight01() {
        var carl = new Warrior();
        var jim = new Knight();

        var res = Battle.fight(carl, jim);

        assertAll(
                () -> assertFalse(res, "Knight should have won"),
                () -> assertEquals(res, false, "Knight should have won 2"),
                () -> assertFalse(carl.isAlive(), "Knight should have won"),
                () -> assertFalse(jim.getHP() < 0, "Knight should have won")

        );
    }

    @Test
    @DisplayName("2. Fight - Warrior vs Warrior")
    void fight02() {
        var carl = new Warrior();
        var jim = new Warrior();

        var res = Battle.fight(carl, jim);

        assertAll(
                () -> assertTrue(res, "carl should have won"),
                () -> assertEquals(res, true, "carl should have won"),
                () -> assertFalse(jim.isAlive(), "carl should have won"),
                () -> assertFalse(carl.getHP() < 0, "carl should have won")

        );
    }

    @Test
    @DisplayName("3. Fight - Knight vs Knight")
    void fight03() {
        var carl = new Knight();
        var jim = new Knight();

        var res = Battle.fight(carl, jim);

        assertAll(
                () -> assertTrue(res, "carl should have won"),
                () -> assertEquals(res, true, "carl should have won 2"),
                () -> assertFalse(jim.isAlive(), "carl should have won"),
                () -> assertFalse(carl.getHP() < 0, "carl should have won")

        );
    }

    @Test
    @DisplayName("4. Fight - Knight vs Warrior")
    void fight04() {
        var carl = new Knight();
        var jim = new Warrior();

        var res = Battle.fight(carl, jim);

        assertAll(
                () -> assertTrue(res, "carl should have won"),
                () -> assertEquals(res, true, "carl should have won 2"),
                () -> assertFalse(jim.isAlive(), "carl should have won"),
                () -> assertFalse(carl.getHP() < 0, "carl should have won")

        );
    }

    @Test
    @DisplayName("5. Fight - tired Knight vs Warrior")
    void fight05_tiredKnight() {
        var carl = new Warrior();
        var jim = new Knight();
        jim.setHP(20);

        var res = Battle.fight(carl, jim);

        assertAll(
                () -> assertTrue(res, "Warrior should have won"),
                () -> assertEquals(res, true, "Warrior should have won 2")

        );
    }

    @ParameterizedTest
    @MethodSource("provideArmiesForFirstBattle")
    @DisplayName("1. Battle - Less warriors attack vs more warriors defend")
    void battle_01(Army firstArmy, Army secondArmy) {

        //when
        boolean resOfBattle = Battle.battle(firstArmy, secondArmy);

        //then
        assertAll(
                () -> assertFalse(resOfBattle, "Army nr 2 should have won"),
                () -> assertEquals(resOfBattle, false, "Army nr 2 should have won - nr 2")

        );
    }

    @ParameterizedTest
    @CsvSource({"2,2", "12,11", "111,110"})
    @DisplayName("2. Battle - More Or Equal warriors attack vs Less Or Equal warriors defend")
    void battle_02(int firstArmySize, int secondArmySize) {
        //given
        Army armyFactory = new Army();
        Army armyOfWarriors1 = armyFactory.addUnits(new Warrior()::clone, firstArmySize);

        Army armyFactory2 = new Army();
        Army armyOfWarriors2 = armyFactory2.addUnits(new Warrior()::clone, secondArmySize);

        //when
        boolean resOfBattle = Battle.battle(armyOfWarriors1, armyOfWarriors2);

        //then
        assertAll(
                () -> assertTrue(resOfBattle, "Army nr 1 should have won"),
                () -> assertEquals(resOfBattle, true, "Army nr 1 should have won - nr 2")

        );
    }

    @ParameterizedTest
    @CsvSource({"1,2", "10,12", "30,38"})
    @DisplayName("3. Battle - Less Knights attack vs more Knights defend")
    void battle_03(int firstArmySize, int secondArmySize) {
        //given
        Army armyFactory = new Army();
        Army armyOfKnights1 = armyFactory.addUnits(new Knight()::clone, firstArmySize);

        Army armyFactory2 = new Army();
        Army armyOfKnights2 = armyFactory2.addUnits(new Knight()::clone, secondArmySize);

        //when
        boolean resOfBattle = Battle.battle(armyOfKnights1, armyOfKnights2);

        //then
        assertAll(
                () -> assertFalse(resOfBattle, "Army nr 2 should have won"),
                () -> assertEquals(resOfBattle, false, "Army nr 2 should have won - nr 2")

        );
    }


    @ParameterizedTest
    @CsvSource({"2,2", "12,11", "111,110"})
    @DisplayName("4. Battle - More Or Equal Knights vs Less Or Equal Knights defend")
    void battle_04(int firstArmySize, int secondArmySize) {
        //given
        Army armyFactory = new Army();
        Army armyOfKnights1 = armyFactory.addUnits(new Knight()::clone, firstArmySize);

        Army armyFactory2 = new Army();
        Army armyOfKnights2 = armyFactory2.addUnits(new Knight()::clone, secondArmySize);

        //when
        boolean resOfBattle = Battle.battle(armyOfKnights1, armyOfKnights2);

        //then
        assertAll(
                () -> assertTrue(resOfBattle, "Army nr 1 should have won"),
                () -> assertEquals(resOfBattle, true, "Army nr 1 should have won - nr 2")

        );
    }

    @ParameterizedTest
    @MethodSource("provideArmiesFor5thBattle")
    @DisplayName("5. Battle - Little Bit More Warriors Attack Should Loose vs Little Bit Less Knights defend Should Win")
    void battle_05(Army firstArmy, Army secondArmy) {

        //when
        boolean resOfBattle = Battle.battle(firstArmy, secondArmy);

        //then
        assertAll(
                () -> assertFalse(resOfBattle, "Army of Knights should have successfully defended"),
                () -> assertEquals(resOfBattle, false, "Army of Knights should have successfully defended - nr 2")

        );
    }


    public static Stream<Arguments> provideArmiesForFirstBattle() {

        return Stream.of(
                Arguments.of(
                        new Army().addUnits((() -> new Warrior()),1).addUnits((()->new Warrior()),1),
                        new Army().addUnits(() -> new Warrior(), 3),
                        true),
                        Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 10),
                        new Army().addUnits(() -> new Warrior(), 13),
                        true),
                        Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 30),
                        new Army().addUnits(() -> new Warrior(), 38),
                        true));
    }

    public static Stream<Arguments> provideArmiesFor5thBattle() {

        return Stream.of(
                Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 12),
                        new Army().addUnits(() -> new Knight(), 11),
                        true),
                Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 61),
                        new Army().addUnits(() -> new Knight(), 60),
                        true),
                Arguments.of(
                        new Army().addUnits(() -> new Warrior(), 210),
                        new Army().addUnits(() -> new Knight(), 208),
                        true));
    }
}

