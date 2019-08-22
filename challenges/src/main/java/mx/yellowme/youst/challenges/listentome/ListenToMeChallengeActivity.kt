package mx.yellowme.youst.challenges.listentome

import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.common.BaseChallengeActivity
import mx.yellowme.youst.challenges.common.ChallengeWithListActivity
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.challenges.domain.PokemonType
import mx.yellowme.youst.challenges.domain.PokemonTypeSelection
import mx.yellowme.youst.challenges.pokemon.types.PokemonTypeAdapter
import mx.yellowme.youst.challenges.pokemon.types.PokemonTypeListViewHolder
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter
import java.util.*

class ListenToMeChallengeActivity : ChallengeWithListActivity<PokemonTypeSelection, PokemonTypeListViewHolder>() {

    override val layoutId = R.layout.challenge_listen_to_me

    override val currentType = ChallengeType.LISTEN_TO_ME

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
