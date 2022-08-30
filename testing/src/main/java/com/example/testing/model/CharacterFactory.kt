package com.example.testing.model

import com.example.core.domain.model.Character


class CharacterFactory {

    fun create(hero: Hero) = when (hero) {
        Hero.TreedDMan -> Character(
            "3D-MAN",
            "https://kanto.legiaodosherois.com.br/w728-h381-gnw-cfill-gcc-f:fbcover/wp-content/uploads/2021/08/legiao_5BscVaJ_S6p7.jpg"
        )

        Hero.ABomb -> Character(
            "A-Bomb (HAS)",
            "https://kanto.legiaodosherois.com.br/w728-h381-gnw-cfill-gcc-f:fbcover/wp-content/uploads/2021/08/legiao_5BscVaJ_S6p7.jpg"
        )
    }

    sealed class Hero {
        object TreedDMan : Hero()
        object ABomb : Hero()
    }
}