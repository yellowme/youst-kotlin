package mx.yellowme.youst.portfolio

import mx.yellowme.youst.core.extensions.launchBrowser
import mx.yellowme.youst.core.extensions.launchPlayStore
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.templates.showcase.ModelTransformer
import mx.yellowme.youst.core.utils.asJsonArrayOf
import mx.yellowme.youst.portfolio.domain.PortfolioElement
import mx.yellowme.youst.portfolio.domain.PortfolioElementType

class PortfolioActivity : GenericShowcaseActivity<PortfolioElement>() {
    override val titleResId: Int = R.string.portfolio_title

    override val subtitleResId: Int = R.string.portfolio_subtitle

    override val optionsJsonName: String = "portfolio.json"

    override val modelTransformer = object : ModelTransformer<PortfolioElement> {
        override fun asList(rawString: String): List<PortfolioElement> {
            return rawString.asJsonArrayOf()!!
        }
    }

    // TODO: Improve item handle
    override fun onItemClick(item: PortfolioElement?) {
        item?.let {
            when (it.type) {
                PortfolioElementType.WEB_PAGE -> {
                    launchBrowser(it.url)
                }
                PortfolioElementType.MOBILE_APP -> {
                    launchPlayStore(it.url)
                }
                else -> throw RuntimeException("Invalid type")
            }
        } ?: throw RuntimeException("Invalid item clicked")
    }
}
