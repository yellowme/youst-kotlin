package mx.yellowme.youst.portfolio.domain

import mx.yellowme.youst.core.domain.GenericShowcasedOption

class PortfolioElement(
    override val id: String,
    override val title: String,
    override val subtitle: String,
    override val paletteColor: String,
    val type: PortfolioElementType,
    val url: String
) : GenericShowcasedOption

enum class PortfolioElementType {
    WEB_PAGE, MOBILE_APP
}
