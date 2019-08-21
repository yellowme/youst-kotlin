package mx.yellowme.youst.core.templates.showcase

import android.os.Bundle
import kotlinx.android.synthetic.main.template_generic_showcase.*
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.utils.dipToPx
import mx.yellowme.youst.core.utils.loadJsonArray

abstract class GenericShowcaseActivity : BaseActivity(), ItemListener<GenericShowcasedOption> {

    private var mCardAdapter: CardPagerAdapter? = null
    private var mCardShadowTransformer: ShadowTransformer? = null

    override val layoutId = R.layout.template_generic_showcase

    abstract val titleResId: Int

    abstract val subtitleResId: Int

    abstract val optionsJsonName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCardAdapter = CardPagerAdapter(this)

        titleTextView.text = getString(titleResId)
        subtitleTextView.text = getString(subtitleResId)

        loadJsonArray<GenericShowcasedOption>(optionsJsonName)?.let {
            mCardAdapter?.addItems(it)
        } ?: throw RuntimeException("Reading corrupted dashboard JSON file")

        showcaseItemsViewPager?.pageMargin = dipToPx(12)
        setupViewPager()
    }

    private fun setupViewPager() {
        mCardAdapter?.let {
            mCardShadowTransformer = ShadowTransformer(
                showcaseItemsViewPager,
                it
            ).apply {
                enableScaling(true)
            }
            showcaseItemsViewPager?.adapter = it
            showcaseItemsViewPager?.setPageTransformer(false, mCardShadowTransformer)
            showcaseItemsViewPager?.offscreenPageLimit = 3
        }
    }
}
