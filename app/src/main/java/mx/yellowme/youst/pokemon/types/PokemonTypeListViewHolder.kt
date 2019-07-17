package mx.yellowme.youst.pokemon.types

import android.content.Context
import android.view.View
import android.widget.CheckBox
import mx.yellowme.youst.R
import mx.yellowme.youst.common.RecyclerViewHolderDecorable
import mx.yellowme.youst.components.TriangularView
import mx.yellowme.youst.domain.PokemonTypeSelection

/**
 * TODO: Add docs
 */
class PokemonTypeListViewHolder internal constructor(
    itemView: View
) : RecyclerViewHolderDecorable<PokemonTypeSelection>(itemView) {

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
