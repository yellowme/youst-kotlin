package mx.yellowme.youst.core.domain

import android.content.Context
import androidx.core.content.ContextCompat
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
            ALL -> colorWithId = R.color.gray
            BUG -> colorWithId = R.color.green_brown
            DRAGON -> colorWithId = R.color.blue_green
            ELECTRIC -> colorWithId = R.color.gold
            FIGHTING -> colorWithId = R.color.orange
            FIRE -> colorWithId = R.color.dark_red
            FLYING -> colorWithId = R.color.blue_gray
            GHOST -> colorWithId = R.color.gray_purple
            DARK -> colorWithId = R.color.black
            FAIRY -> colorWithId = R.color.dark_pink
            GRASS -> colorWithId = R.color.light_green
            GROUND -> colorWithId = R.color.light_brown
            NORMAL -> colorWithId = R.color.gray_brown
            ICE -> colorWithId = R.color.bright_blue
            POISON -> colorWithId = R.color.purple
            PSYCHIC -> colorWithId = R.color.hot_pink
            ROCK -> colorWithId = R.color.dark_brown
            STEEL -> colorWithId = R.color.gray_green
            WATER -> colorWithId = R.color.blue
        }

        return ContextCompat.getColor(fromContext, colorWithId)
    }
}