package mx.yellowme.youst.challenges.domain

import mx.yellowme.youst.core.domain.GenericShowcasedOption

class Challenge(
    override val id: String,
    override val title: String,
    override val subtitle: String,
    override val hexColor: String
) : GenericShowcasedOption {

    val help: String? = null

    val type: ChallengeType
        get() {
            return when (id) {
                "1" -> ChallengeType.CRAZY_LISTS
                "2" -> ChallengeType.LISTEN_TO_ME
                "3" -> ChallengeType.ABOUT_DETAILS
                "4" -> ChallengeType.ARCHIE
                else -> throw RuntimeException("Unsupported challenge type for id: $id")
            }
        }
}

enum class ChallengeType {
    CRAZY_LISTS, LISTEN_TO_ME, ABOUT_DETAILS, ARCHIE
}
