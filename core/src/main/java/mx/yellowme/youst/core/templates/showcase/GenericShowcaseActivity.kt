package mx.yellowme.youst.core.templates.showcase

import android.os.Bundle
import kotlinx.android.synthetic.main.template_generic_showcase.*
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.domain.GenericShowcasedOption
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.utils.dipToPx
import mx.yellowme.youst.core.utils.readJson

interface ModelTransformer<Model> {
    fun asList(rawString: String): List<Model>
}

abstract class GenericShowcaseActivity<Model : GenericShowcasedOption> : BaseActivity(),
    ItemListener<Model> {

    private var cardAdapter: CardPagerAdapter<Model>? = null
    private var shadowTransformer: ShadowTransformer? = null

    override val layoutId = R.layout.template_generic_showcase

    abstract val titleResId: Int

    abstract val subtitleResId: Int

    abstract val optionsJsonName: String

    abstract val modelTransformer: ModelTransformer<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardAdapter = CardPagerAdapter(this)

        titleTextView.text = getString(titleResId)
        subtitleTextView.text = getString(subtitleResId)

        readJson(optionsJsonName)?.let {
            cardAdapter?.addItems(modelTransformer.asList(it))
        } ?: throw RuntimeException("Unable to read JSON file named $optionsJsonName")

        showcaseItemsViewPager?.pageMargin = dipToPx(12)
        setupViewPager()
    }

    private fun setupViewPager() {
        cardAdapter?.let {
            shadowTransformer = ShadowTransformer(
                showcaseItemsViewPager,
                it
            ).apply {
                enableScaling(true)
            }
            showcaseItemsViewPager?.adapter = it
            showcaseItemsViewPager?.setPageTransformer(false, shadowTransformer)
            showcaseItemsViewPager?.offscreenPageLimit = 3
        }
    }
}
