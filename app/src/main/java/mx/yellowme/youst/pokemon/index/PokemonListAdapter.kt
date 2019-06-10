package mx.yellowme.youst.components.pokemon.index;

import android.view.LayoutInflater
import android.view.ViewGroup
import mx.yellowme.youst.R
import mx.yellowme.youst.common.ItemListener
import mx.yellowme.youst.common.SimpleRecyclerAdapter
import mx.yellowme.youst.domain.Pokemon

/**
 * MainModel adapter for displaying a list
 * <p>
 * Created by luisburgos.
 */

class PokemonListAdapter(
    items: MutableList<Pokemon>,
    itemListener: ItemListener<Pokemon>
) : SimpleRecyclerAdapter<Pokemon, PokemonListViewHolder>(items, itemListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_item_pokemon, parent, false)

        return PokemonListViewHolder(view, itemListener)
    }

}
