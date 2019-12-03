package mx.yellowme.youst.challenges.ui.archie

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.challenge_archie.*
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.common.BaseChallengeActivity
import mx.yellowme.youst.challenges.components.GenericListModel
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.challenges.domain.Pokemon
import mx.yellowme.youst.challenges.pokemon.PokemonListLoader
import mx.yellowme.youst.challenges.pokemon.index.PokemonListAdapter
import mx.yellowme.youst.challenges.pokemon.index.PokemonListViewHolder
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter
import java.util.*

/**
 * @author adrianleyvasanchez
 * @since 03,December,2019
 */
class ArchieChallengeActivity : BaseChallengeActivity() {

    //region Attributes

    override val layoutId = R.layout.challenge_archie

    override val currentType = ChallengeType.ARCHIE

    //endregion

    //region Lifecycle

    override fun onResume() {
        super.onResume()
        PokemonListLoader.loadData(pokeListModel)
    }

    //endregion


}

class PokeListModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GenericListModel<Pokemon, PokemonListViewHolder>(context, attrs, defStyleAttr) {
    override fun initAdapter(): SimpleRecyclerAdapter<Pokemon, PokemonListViewHolder>? {
        return PokemonListAdapter(ArrayList(), object : ItemListener<Pokemon> {
            override fun onItemClick(item: Pokemon?) {
                //TODO: Implement it
            }
        })
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 3)

    }
}
