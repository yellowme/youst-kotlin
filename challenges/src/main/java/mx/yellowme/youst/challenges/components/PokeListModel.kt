package mx.yellowme.youst.challenges.components

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.challenges.domain.Pokemon
import mx.yellowme.youst.challenges.pokemon.index.PokemonListAdapter
import mx.yellowme.youst.challenges.pokemon.index.PokemonListViewHolder
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter

class PokeListModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GenericListModel<Pokemon, PokemonListViewHolder>(context, attrs, defStyleAttr) {
    override fun initAdapter(): SimpleRecyclerAdapter<Pokemon, PokemonListViewHolder>? {
        return PokemonListAdapter(ArrayList(), object : ItemListener<Pokemon> {
            override fun onItemClick(item: Pokemon?) {
                // TODO: Implement it
            }
        })
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 3)
    }
}
