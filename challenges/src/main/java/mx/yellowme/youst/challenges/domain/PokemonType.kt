package mx.yellowme.youst.challenges.domain

import android.content.Context
import android.util.TypedValue
import mx.yellowme.youst.core.R

/**
 * TODO: Add docs
 */
enum class PokemonType {
    ALL, BUG, DRAGON,
    ELECTRIC, FIGHTING, FIRE,
    FLYING, GHOST, DARK,
    FAIRY, GRASS, GROUND,
    ICE, NORMAL, POISON,
    PSYCHIC, ROCK, STEEL, WATER;

    /**
     * TODO: Add docs
     */
    fun getColorResource(fromContext: Context): Int {
        var colorWithId = -1

        when (this) {
            ALL -> colorWithId = R.attr.typeAll
            BUG -> colorWithId = R.attr.typeBug
            DRAGON -> colorWithId = R.attr.typeDragon
            ELECTRIC -> colorWithId = R.attr.typeElectric
            FIGHTING -> colorWithId = R.attr.typeFighting
            FIRE -> colorWithId = R.attr.typeFire
            FLYING -> colorWithId = R.attr.typeFlying
            GHOST -> colorWithId = R.attr.typeGhost
            DARK -> colorWithId = R.attr.typeDark
            FAIRY -> colorWithId = R.attr.typeFairy
            GRASS -> colorWithId = R.attr.typeGrass
            GROUND -> colorWithId = R.attr.typeGround
            NORMAL -> colorWithId = R.attr.typeNormal
            ICE -> colorWithId = R.attr.typeIce
            POISON -> colorWithId = R.attr.typePoison
            PSYCHIC -> colorWithId = R.attr.typePsychic
            ROCK -> colorWithId = R.attr.typeRock
            STEEL -> colorWithId = R.attr.typeSteel
            WATER -> colorWithId = R.attr.typeWater
        }
        val typedValue = TypedValue()
        fromContext.theme.resolveAttribute(colorWithId, typedValue, true)
        return typedValue.data
    }
}
