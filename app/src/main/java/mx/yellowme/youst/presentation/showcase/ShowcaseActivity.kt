package mx.yellowme.youst.presentation.showcase

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_showcase.*
import mx.yellowme.youst.R
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.presentation.challenges.BaseChallengeActivity.Companion.TOOLBAR_TITLE
import mx.yellowme.youst.presentation.showcase.cards.CardPagerAdapter
import mx.yellowme.youst.presentation.showcase.cards.ShadowTransformer
import mx.yellowme.youst.core.domain.Challenge
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.utils.dipToPx
import mx.yellowme.youst.data.ChallengeDataHelper.loadChallengesFromJSONUsing
import mx.yellowme.youst.presentation.challenges.CrazyListsChallengeActivity
import mx.yellowme.youst.presentation.challenges.ListenToMeChallengeActivity
import mx.yellowme.youst.presentation.challenges.navigation.NavigationActivity

class ShowcaseActivity : BaseActivity(), ItemListener<Challenge> {

    private var mCardAdapter: CardPagerAdapter? = null
    private var mCardShadowTransformer: ShadowTransformer? = null

    override val layoutId = R.layout.screen_showcase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCardAdapter = CardPagerAdapter(this)
        mCardAdapter?.addItems(loadChallengesFromJSONUsing(classLoader)!!)

        challengesViewPager?.pageMargin = dipToPx(12)
        setupViewPager()
    }

    override fun onItemClick(item: Challenge?) {
        item?.type?.let {
            if (it == Challenge.ChallengeType.BLACK) {
                toast(getString(R.string.work_in_progress))
                return
            }

            val nextActivity: Class<*> = when (it) {
                Challenge.ChallengeType.MAROON -> {
                    ListenToMeChallengeActivity::class.java
                }
                Challenge.ChallengeType.BLUE,
                Challenge.ChallengeType.BLUE_GREEN -> {
                    CrazyListsChallengeActivity::class.java
                }
                Challenge.ChallengeType.EMERALD -> NavigationActivity::class.java
                else -> throw RuntimeException("Invalid challenge identifier")
            }

            launch(nextActivity) {
                putExtra(TOOLBAR_TITLE, item.title)
            }
        }
    }

    private fun setupViewPager() {
        mCardAdapter?.let {
            mCardShadowTransformer = ShadowTransformer(challengesViewPager, it).apply {
                enableScaling(true)
            }
            challengesViewPager?.adapter = it
            challengesViewPager?.setPageTransformer(false, mCardShadowTransformer)
            challengesViewPager?.offscreenPageLimit = 3
        }
    }
}
