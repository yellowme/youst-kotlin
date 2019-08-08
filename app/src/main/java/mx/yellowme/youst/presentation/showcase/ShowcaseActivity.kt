package mx.yellowme.youst.presentation.showcase

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_showcase.*
import mx.yellowme.youst.R
import mx.yellowme.youst.common.ItemListener
import mx.yellowme.youst.common.activities.BaseActivity
import mx.yellowme.youst.common.activities.BaseChallengeActivity.Companion.TOOLBAR_TITLE
import mx.yellowme.youst.common.cards.CardPagerAdapter
import mx.yellowme.youst.common.cards.ShadowTransformer
import mx.yellowme.youst.common.start
import mx.yellowme.youst.data.ChallengeDataHelper.loadChallengesFromJSONUsing
import mx.yellowme.youst.domain.Challenge
import mx.yellowme.youst.presentation.challenges.CrazyListsChallengeActivity
import mx.yellowme.youst.presentation.challenges.ListenToMeChallengeActivity
import mx.yellowme.youst.presentation.challenges.navigation.NavigationActivity
import mx.yellowme.youst.utils.dipTopx

class ShowcaseActivity : BaseActivity(), ItemListener<Challenge> {
    private var mCardAdapter: CardPagerAdapter? = null
    private var mCardShadowTransformer: ShadowTransformer? = null

    override val layoutResource: Int
        get() = R.layout.screen_showcase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCardAdapter = CardPagerAdapter(this)
        mCardAdapter?.addItems(loadChallengesFromJSONUsing(classLoader)!!)
        setupViewPager()
    }

    override fun bindViews() {
        challengesViewPager?.pageMargin = dipTopx(12)
    }

    override fun onItemClick(item: Challenge?) {
        item?.type?.let {
            if (it == Challenge.ChallengeType.BLACK) {
                challengeAtWIP()
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

            start(nextActivity, hashMapOf(TOOLBAR_TITLE to item.title))
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
