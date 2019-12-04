package mx.yellowme.youst.challenges.ui.listentome

import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.challenges.common.BaseChallengeActivity
import mx.yellowme.youst.challenges.domain.ChallengeType

class ListenToMeChallengeActivity : BaseChallengeActivity() {

    override val layoutId = R.layout.challenge_listen_to_me

    override val currentType = ChallengeType.LISTEN_TO_ME

}
