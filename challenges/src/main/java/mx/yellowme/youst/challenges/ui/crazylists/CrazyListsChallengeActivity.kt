package mx.yellowme.youst.challenges.ui.crazylists

import kotlinx.android.synthetic.main.challenge_crazy_lists.pokeListModel
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.common.BaseChallengeActivity
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.challenges.domain.Pokemon
import mx.yellowme.youst.challenges.pokemon.PokemonListLoader
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.recycler.ItemListener

class CrazyListsChallengeActivity : BaseChallengeActivity(),
    ItemListener<Pokemon> {
    override val layoutId = R.layout.challenge_crazy_lists

    override val currentType = ChallengeType.CRAZY_LISTS

    override fun onResume() {
        super.onResume()
        PokemonListLoader.loadData(pokeListModel)
    }

    override fun onItemClick(item: Pokemon?) {
        item?.name?.let {
            toast(it)
        }
    }
}
