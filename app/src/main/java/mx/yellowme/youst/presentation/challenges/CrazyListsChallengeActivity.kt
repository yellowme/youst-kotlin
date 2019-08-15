package mx.yellowme.youst.presentation.challenges

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.R
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter
import mx.yellowme.youst.pokemon.index.PokemonListAdapter
import mx.yellowme.youst.pokemon.index.PokemonListViewHolder
import mx.yellowme.youst.core.domain.Challenge
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.data.PokemonListLoader
import mx.yellowme.youst.domain.Pokemon
import java.util.*

class CrazyListsChallengeActivity : ChallengeWithListActivity<Pokemon, PokemonListViewHolder>(),
    BaseChallengeActivity.MenuActionsListener,
    ItemListener<Pokemon> {

    override val layoutId = R.layout.screen_challenge_crazy_lists

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

    override fun onClickHelpMenu() {
        dialogWithHelpFor(Challenge.ChallengeType.BLUE)
    }
}
