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
        warrior.equipWeapon(MagicWand());
        warrior.equipWeapon(GreatAxe());
        warrior.equipWeapon(Sword());

        //when
        //then
        boolean res = Battle.fight(knight, warrior);

        assertFalse(res);
    }

    @Test
    @DisplayName("Softserve site smoke tests for equipped warrior/army - straight fight")
    void smokeTest() {

        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();


        var sword = Sword();
        var shield = Shield();
        var axe = GreatAxe();
        var katana = Katana();
        var wand = MagicWand();
        var superWeapon = Weapon.builder().hp(50).attack(10).defence(5).vampirism(150).healPower(8).build();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertEquals(ogre.getHP(), 125);
        assertEquals(lancelot.getAttack(), 17);
        assertEquals(richard.getDefense(), 4);
        assertEquals(eric.getVampirism(), 200);
        assertEquals(freelancer.getHP(), 15);
        assertEquals(priest.getHealingPower(), 5);

        assertFalse(Battle.fight(ogre, eric));
        assertFalse(Battle.fight(priest, richard));
        assertTrue(Battle.fight(lancelot, freelancer));

        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        boolean res = Battle.straightFight(myArmy, enemyArmy);

        assertTrue(res);
    }

/**
    This time tested with battle (one behind one)
 */

    @Test
    @DisplayName("Softserve site smoke tests for equipped warrior/army - battle")
    void smokeTest2() {

        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();


        var sword = Sword();
        var shield = Shield();
        var axe = GreatAxe();
        var katana = Katana();
        var wand = MagicWand();
        var superWeapon = Weapon.builder().hp(50).attack(10).defence(5).vampirism(150).healPower(8).build();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertEquals(ogre.getHP(), 125);
        assertEquals(lancelot.getAttack(), 17);
        assertEquals(richard.getDefense(), 4);
        assertEquals(eric.getVampirism(), 200);
        assertEquals(freelancer.getHP(), 15);
        assertEquals(priest.getHealingPower(), 5);

        assertFalse(Battle.fight(ogre, eric));
        assertFalse(Battle.fight(priest, richard));
        assertTrue(Battle.fight(lancelot, freelancer));

        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        boolean res = Battle.battle(myArmy, enemyArmy);

        assertTrue(res);
    }

    /**
     * Another part of last test from BattleTest class after equipping attacking Warriors
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
