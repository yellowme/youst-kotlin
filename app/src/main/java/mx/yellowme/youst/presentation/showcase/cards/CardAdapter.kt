package mx.yellowme.youst.presentation.showcase.cards

import androidx.cardview.widget.CardView

/**
 * TODO: Add docs
 */
interface CardAdapter {

    val baseElevation: Float

    fun getCardViewAt(position: Int): CardView?

    companion object {
        const val MAX_ELEVATION_FACTOR = 4
    }

    fun getCount(): Int

}
