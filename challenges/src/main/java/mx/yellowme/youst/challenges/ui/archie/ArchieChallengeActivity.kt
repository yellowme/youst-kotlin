package mx.yellowme.youst.challenges.ui.archie

import kotlinx.android.synthetic.main.challenge_archie.*
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.common.BaseChallengeActivity
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.challenges.pokemon.PokemonListLoader

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
