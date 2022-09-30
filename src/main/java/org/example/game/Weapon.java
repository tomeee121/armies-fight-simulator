package org.example.game;

import lombok.*;

@Getter
@Setter
@Builder
class Weapon implements WeaponI {
    private int hp;
    private int attack;
    private int defence;
    private int vampirism;
    private int healPower;
}
