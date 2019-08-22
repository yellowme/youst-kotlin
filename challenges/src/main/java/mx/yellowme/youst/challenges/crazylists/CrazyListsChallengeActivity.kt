package mx.yellowme.youst.challenges.crazylists

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.common.ChallengeWithListActivity
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.challenges.domain.Pokemon
import mx.yellowme.youst.challenges.pokemon.PokemonListLoader
import mx.yellowme.youst.challenges.pokemon.index.PokemonListAdapter
import mx.yellowme.youst.challenges.pokemon.index.PokemonListViewHolder
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter
import java.util.*

class CrazyListsChallengeActivity : ChallengeWithListActivity<Pokemon, PokemonListViewHolder>(),
    ItemListener<Pokemon> {

    override val layoutId = R.layout.challenge_crazy_lists

    override val currentType = ChallengeType.CRAZY_LISTS

    override fun onResume() {
        super.onResume()
        PokemonListLoader.loadData(this)
    }

    override fun initAdapter(): SimpleRecyclerAdapter<Pokemon, PokemonListViewHolder>? {
        return PokemonListAdapter(ArrayList(), this)
    }

    override fun onItemClick(item: Pokemon?) {
        item?.name?.let {
            toast(it)
        }
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(this, 3)
    }

}
