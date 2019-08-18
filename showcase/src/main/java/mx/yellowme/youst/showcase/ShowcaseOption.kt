package mx.yellowme.youst.showcase

data class ShowcaseOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val hexColor: String
) {
    val optionId: ShowcaseOptionId
        get() {
            return ShowcaseOptionId.valueOf(id.toUpperCase())
        }
}

enum class ShowcaseOptionId {
    CHALLENGES,
    PLAYGROUND,
    MINI_APPS,
    PORTFOLIO
}