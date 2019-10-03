package mx.yellowme.youst.core.components

import mx.yellowme.youst.core.R

enum class PaletteColors {
    PASTEL_RED,
    DARK_MOUNTAIN,
    JOUST_BLUE,
    NASU_PURPLE,

    PINK_GLAMOUR,
    MINT_LEAF,
    ELECTRON_BLUE,
    EXODUS_FRUIT,
    CHI_GONG,

    ICELAND_POPPY,
    WATERFALL,
    FOREST_BLUES,
    JALAPENO_RED,

    BRUSCHETTA_TOMATO,
    UFO_GREEN,
    CLEAR_CHILL,
    SATURATED_SKY,
    TANGERINE,

    TURQUOISE,
    PETERRIVER,
    AMETHYST,
    NON_BLACK,
    MAZARINE_BLUE,
    MERCHANT_MARINE,
    SKIRRET_GREEN,
    SYNTHETIC_PUMPKIN;

    val hexColor: Int
        get() {
            return when (this) {
                PASTEL_RED -> R.attr.cerise
                DARK_MOUNTAIN -> R.attr.turquoise
                JOUST_BLUE -> R.attr.malibu
                NASU_PURPLE -> R.attr.violet

                PINK_GLAMOUR -> R.attr.coral
                MINT_LEAF -> R.attr.turquoise
                ELECTRON_BLUE -> R.attr.malibu
                EXODUS_FRUIT -> R.attr.violet
                CHI_GONG -> R.attr.cerise

                ICELAND_POPPY -> R.attr.coral
                WATERFALL -> R.attr.turquoise
                FOREST_BLUES -> R.attr.royal
                JALAPENO_RED -> R.attr.cerise

                BRUSCHETTA_TOMATO -> R.attr.coral
                UFO_GREEN -> R.attr.kiwi
                CLEAR_CHILL -> R.attr.malibu
                SATURATED_SKY -> R.attr.violet

                TANGERINE -> R.attr.coral
                TURQUOISE -> R.attr.turquoise
                PETERRIVER -> R.attr.malibu
                AMETHYST -> R.attr.orchid
                NON_BLACK -> R.attr.typeDark
                MAZARINE_BLUE -> R.attr.royal
                MERCHANT_MARINE -> R.attr.royal
                SKIRRET_GREEN -> R.attr.kiwi
                SYNTHETIC_PUMPKIN -> R.attr.coral
            }
        }
}

/**
 * Reference: https://flatuicolors.com/
 */
object Palette {
    object Main {
        val tangerine = "#ff9a7b"
        val turquoise = "#1abc9c"
        val peterriver = "#3498db"
        val amethyst = "#9b59b6"
        val mazarineBlue = "#273c75"
        val merchantMarineBlue = "#0652DD"
        val skirretGreen = "#44bd32"
        val syntheticPumpkin = "#ff793f"
        val nonBlack = "#111111"
    }

    /**
     * Reference: https://flatuicolors.com/palette/ca
     */
    object Canadian {
        val pastelRed = "#ff6b6b"
        val darkMountainMeadow = "#10ac84"
        val joustBlue = "#54a0ff"
        val nasuPurple = "#5f27cd"
    }

    /**
     * Reference: https://flatuicolors.com/palette/us
     */
    object American {
        val pinkGlamour = "#e17055"
        val electronBlue = "#0984e3"
        val mintLeaf = "#00b894"
        val exodusFruit = "#6c5ce7"
        val chiGong = "#d63031"
    }

    /**
     * Reference: https://flatuicolors.com/palette/fr
     */
    object French {
        val icelandPoppy = "#fa983a"
        val waterfall = "#38ada9"
        val forestBlues = "#0a3d62"
        val jalapenoRed = "#b71540"
    }

    /**
     * Reference: https://flatuicolors.com/palette/cn
     */
    object Chinese {
        val bruschettaTomato = "#ff6348"
        val ufoGreen = "#2ed573"
        val clearChill = "#1e90ff"
        val saturatedSky = "#5352ed"
        val brightGreek = "#3742fa"
    }
}
