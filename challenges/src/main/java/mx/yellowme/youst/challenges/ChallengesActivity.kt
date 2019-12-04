package mx.yellowme.youst.challenges

import mx.yellowme.youst.challenges.common.BaseChallengeActivity.Companion.TOOLBAR_TITLE
import mx.yellowme.youst.challenges.ui.crazylists.CrazyListsChallengeActivity
import mx.yellowme.youst.challenges.domain.Challenge
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.challenges.ui.listentome.ListenToMeChallengeActivity
import mx.yellowme.youst.challenges.ui.archie.ArchieChallengeActivity
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.templates.showcase.ModelTransformer
import mx.yellowme.youst.core.utils.asJsonArrayOf

class ChallengesActivity : GenericShowcaseActivity<Challenge>() {

    override val titleResId: Int = R.string.challenges_title

    override val subtitleResId: Int = R.string.challenges_subtitle

    override val optionsJsonName: String = "challenges.json"

    override val modelTransformer = object : ModelTransformer<Challenge> {
        override fun asList(rawString: String): List<Challenge> {
            return rawString.asJsonArrayOf()!!
        }
    }

    //TODO: Improve item handle
    override fun onItemClick(item: Challenge?) {
        item?.type?.let {
            val nextActivity: Class<*> = when (it) {
                ChallengeType.LISTEN_TO_ME -> {
                    ListenToMeChallengeActivity::class.java
                }
                ChallengeType.CRAZY_LISTS,
                ChallengeType.ABOUT_DETAILS -> {
                    CrazyListsChallengeActivity::class.java
                }
                ChallengeType.ARCHIE -> {
                    ArchieChallengeActivity::class.java
                }
                else -> throw RuntimeException("Invalid type: $it")
            }

            launch(nextActivity) {
                putExtra(TOOLBAR_TITLE, item.title)
            }
        }
    }

}
