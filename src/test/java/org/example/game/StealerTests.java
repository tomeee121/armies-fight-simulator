package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.game.WeaponI.MagicWand;
import static org.example.game.WeaponI.Sword;
import static org.junit.jupiter.api.Assertions.*;

public class StealerTests {

    @Test
    @DisplayName("Weaker Stealer will win vs Knight when he is equipped")
    void stealerSmokeTest() {

/**
        Stealer can defeat Knight by grabbing his Swond and MagicWand (strongest weapon)
*/

        var stealer = new Stealer();
        var knight = new Knight();

        assertEquals(stealer.getAttack(), 3);
        assertEquals(stealer.getHP(), 35);
        assertEquals(stealer.getDefense(), 2);

        assertEquals(knight.getAttack(), 7);
        assertEquals(knight.getHP(), 50);

        knight.equipWeapon(MagicWand());
        knight.equipWeapon(Sword());

        assertEquals(knight.getAttack(), 12);
        assertEquals(knight.getHP(), 85);

        boolean res = Battle.fight(knight, stealer);

        assertFalse(res);

        assertEquals(8, stealer.getAttack());

    }

    @Test
    @DisplayName("Weaker Stealer will loose to Knight without weapons")
    void stealerSmokeTest2() {

/**
    Stealer can not defeat Knight if he has no weapons that can be stealed
 */

        var stealer = new Stealer();
        var knight = new Knight();

        assertEquals(stealer.getAttack(), 3);
        assertEquals(stealer.getHP(), 35);
        assertEquals(stealer.getDefense(), 2);

        assertEquals(knight.getAttack(), 7);
        assertEquals(knight.getHP(), 50);

        assertEquals(knight.getAttack(), 7);
        assertEquals(knight.getHP(), 50);

        boolean res = Battle.fight(knight, stealer);

        assertTrue(res);

        assertEquals(3, stealer.getAttack());

    }
}
