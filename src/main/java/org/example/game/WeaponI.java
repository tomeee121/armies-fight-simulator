package org.example.game;

interface WeaponI {

    static Weapon Sword() {
        return Weapon.builder().hp(5).attack(2).build();
    }

    static Weapon Shield() {
        return Weapon.builder().hp(20).attack(-1).defence(2).build();
    }

    static Weapon GreatAxe() {
        return Weapon.builder().hp(-15).attack(5).defence(-2).vampirism(10).build();
    }

    static Weapon Katana() {
        return Weapon.builder().hp(-20).attack(6).defence(-5).vampirism(50).build();
    }

    static Weapon MagicWand() {
        return Weapon.builder().hp(30).attack(3).healPower(3).build();
    }

}
