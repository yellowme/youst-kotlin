package mx.yellowme.youst.challenges.pokemon.types

import android.content.Context
import android.view.View
import android.widget.CheckBox
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.domain.PokemonTypeSelection
import mx.yellowme.youst.core.components.TriangularView
import mx.yellowme.youst.core.hooks.recycler.RecyclerViewHolderDecorator

/**
 * TODO: Add docs
 */
class PokemonTypeListViewHolder internal constructor(
    itemView: View
) : RecyclerViewHolderDecorator<PokemonTypeSelection>(itemView) {
    private val context: Context = itemView.context
    private val typeDecoration: TriangularView = itemView.findViewById(R.id.typeDecoration)
    internal val typeCheckBox: CheckBox = itemView.findViewById(R.id.typeCheckBox)

    override fun decorate(model: PokemonTypeSelection) {
        with(model) {
            type?.let {
                typeCheckBox.text = it.toString()
                typeDecoration.triangleColor = it.getColorResource(context)
            }
        }
    }
}
