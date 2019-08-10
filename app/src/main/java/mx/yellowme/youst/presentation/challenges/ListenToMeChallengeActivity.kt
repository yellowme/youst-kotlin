package mx.yellowme.youst.presentation.challenges

import mx.yellowme.youst.R
import mx.yellowme.youst.common.SimpleRecyclerAdapter
import mx.yellowme.youst.common.activities.BaseChallengeActivity
import mx.yellowme.youst.common.activities.ChallengeWithListActivity
import mx.yellowme.youst.pokemon.types.PokemonTypeAdapter
import mx.yellowme.youst.pokemon.types.PokemonTypeListViewHolder
import mx.yellowme.youst.core.domain.Challenge
import mx.yellowme.youst.domain.PokemonType
import mx.yellowme.youst.domain.PokemonTypeSelection
import java.util.*

class ListenToMeChallengeActivity : ChallengeWithListActivity<PokemonTypeSelection, PokemonTypeListViewHolder>(),
    BaseChallengeActivity.MenuActionsListener {

    override val layoutId = R.layout.screen_challenge_listen_to_me

    override fun initAdapter(): SimpleRecyclerAdapter<PokemonTypeSelection, PokemonTypeListViewHolder>? {
        val types = PokemonType.values()
        val selectionTypes = ArrayList<PokemonTypeSelection>()
        for (type in types) {
            selectionTypes.add(PokemonTypeSelection(type, false))
        }

        return PokemonTypeAdapter(selectionTypes, null)
    }

    override fun onClickHelpMenu() {
        dialogWithHelpFor(Challenge.ChallengeType.MAROON)
    }
}
