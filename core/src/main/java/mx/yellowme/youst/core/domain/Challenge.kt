package mx.yellowme.youst.core.domain

import mx.yellowme.youst.core.R

/**
 * TODO: Add docs
 */
class Challenge {
    val title: String? = null
    val description: String? = null
    val help: String? = null
    val type: ChallengeType? = null

    enum class ChallengeType {
        BLUE, MAROON, BLUE_GREEN, BLACK, EMERALD;

        val resourceId: Int
            get() {
                return when (this) {
                    BLACK -> R.drawable.ic_bookmark_black
                    BLUE -> R.drawable.ic_bookmark_blue
                    BLUE_GREEN -> R.drawable.ic_bookmark_blue_green
                    MAROON -> R.drawable.ic_bookmark_maroon
                    EMERALD -> R.drawable.ic_bookmark_emerald
                }
            }
    }
}
