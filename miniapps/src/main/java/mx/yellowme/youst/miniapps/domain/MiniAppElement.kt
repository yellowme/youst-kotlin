package mx.yellowme.youst.miniapps.domain

import mx.yellowme.youst.core.domain.GenericShowcasedOption

class MiniAppElement(
    override val id: String,
    override val title: String,
    override val subtitle: String,
    override val paletteColor: String
) : GenericShowcasedOption {

    val type: MiniAppElementType
        get() {
            return when (id) {
                "1" -> MiniAppElementType.CHORDS
                "2" -> MiniAppElementType.CONTACT_US
                else -> throw RuntimeException("Unsupported challenge type for id: $id")
            }
        }
}

enum class MiniAppElementType {
    CHORDS,
    CONTACT_US
}
