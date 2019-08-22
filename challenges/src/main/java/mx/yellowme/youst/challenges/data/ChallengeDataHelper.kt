package mx.yellowme.youst.challenges.data

import mx.yellowme.youst.challenges.domain.Challenge
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.core.utils.asJsonArrayOf
import mx.yellowme.youst.core.utils.readJson

object ChallengeDataHelper {
    fun challengeForType(type: ChallengeType, loader: ClassLoader?): Challenge? {
        val challenges = loader?.readJson("challenges.json")?.asJsonArrayOf<Challenge>()
        if (challenges == null || challenges.isEmpty()) {
            return null
        }

        var challengeMatch: Challenge? = null
        challenges.forEach {
            if (it.type == type) {
                challengeMatch = it
                return@forEach
            }
        }
        return challengeMatch
    }
}
