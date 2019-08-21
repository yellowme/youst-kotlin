package mx.yellowme.youst.challenges.pokemon.index

import android.view.LayoutInflater
import android.view.ViewGroup
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.core.domain.Pokemon
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter

/**
 * MainModel adapter for displaying a list.
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
