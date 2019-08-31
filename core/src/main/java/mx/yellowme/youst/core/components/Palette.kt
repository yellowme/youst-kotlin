package mx.yellowme.youst.core.components

enum class PaletteColors {
    PASTEL_RED,
    DARK_MOUNTAIN,
    JOUST_BLUE,
    NASU_PURPLE,

    PINK_GLAMOUR,
    MINT_LEAF,
    ELECTRON_BLUE,
    EXODUS_FRUIT,

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
    AMETHYST;

    val hexColor: String
        get() {
            return when(this) {
                PASTEL_RED -> Palette.Canadian.pastelRed
                DARK_MOUNTAIN -> Palette.Canadian.darkMountainMeadow
                JOUST_BLUE -> Palette.Canadian.joustBlue
                NASU_PURPLE -> Palette.Canadian.nasuPurple
                PINK_GLAMOUR -> Palette.American.pinkGlamour
                MINT_LEAF -> Palette.American.mintLeaf
                ELECTRON_BLUE -> Palette.American.electronBlue
                EXODUS_FRUIT -> Palette.American.exodusFruit
                ICELAND_POPPY -> Palette.French.icelandPoppy
                WATERFALL -> Palette.French.waterfall
                FOREST_BLUES -> Palette.French.forestBlues
                JALAPENO_RED -> Palette.French.jalapenoRed
                BRUSCHETTA_TOMATO -> Palette.Chinese.bruschettaTomato
                UFO_GREEN -> Palette.Chinese.ufoGreen
                CLEAR_CHILL -> Palette.Chinese.clearChill
                SATURATED_SKY -> Palette.Chinese.saturatedSky
                TANGERINE -> Palette.Main.tangerine
                TURQUOISE -> Palette.Main.turquoise
                PETERRIVER -> Palette.Main.peterriver
                AMETHYST -> Palette.Main.amethyst
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
    }
}
