package mx.yellowme.youst.playground.domain

import mx.yellowme.youst.core.domain.GenericShowcasedOption

class PlaygroundElement(
    override val id: String,
    override val title: String,
    override val subtitle: String,
    override val hexColor: String
) : GenericShowcasedOption {

    val type: PlaygroundElementType
        get() {
            return when (id) {
                "1" -> PlaygroundElementType.JETPACK_NAVIGATION
                "2" -> PlaygroundElementType.NEMO
                else -> throw RuntimeException("Unsupported challenge type for id: $id")
            }
        }
}

enum class PlaygroundElementType {
    JETPACK_NAVIGATION, NEMO
}