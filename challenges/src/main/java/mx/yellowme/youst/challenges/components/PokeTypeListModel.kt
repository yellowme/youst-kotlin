package mx.yellowme.youst.challenges.components

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.challenges.domain.PokemonType
import mx.yellowme.youst.challenges.domain.PokemonTypeSelection
import mx.yellowme.youst.challenges.pokemon.types.PokemonTypeAdapter
import mx.yellowme.youst.challenges.pokemon.types.PokemonTypeListViewHolder
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter

class PokeTypeListModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GenericListModel<PokemonTypeSelection, PokemonTypeListViewHolder>(
    context,
    attrs,
    defStyleAttr
) {

    override fun initAdapter(): SimpleRecyclerAdapter<PokemonTypeSelection, PokemonTypeListViewHolder>? {
        val types = PokemonType.values()
        val selectionTypes = ArrayList<PokemonTypeSelection>()
        for (type in types) {
            selectionTypes.add(
                PokemonTypeSelection(
                    type,
                    false
                )
            )
        }

        return PokemonTypeAdapter(selectionTypes, null)
    }

}
