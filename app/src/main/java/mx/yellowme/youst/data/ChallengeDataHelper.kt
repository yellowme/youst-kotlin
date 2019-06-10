package mx.yellowme.youst.data;

import mx.yellowme.youst.domain.AllChallenges
import mx.yellowme.youst.domain.Challenge
import mx.yellowme.youst.utils.loadJsonNamed

object ChallengeDataHelper {
    /**
     * Looks for a JSON file named "challenges.json" that should be placed under the
     * test resources folder.
     */
    fun loadChallengesFromJSONUsing(loader: ClassLoader?): List<Challenge>? {
        val challenges = loader?.loadJsonNamed(
                    "challenges.json", AllChallenges::class.java
        ) as? AllChallenges

        return challenges?.challenges
    }

    fun challengeForId(type: Challenge.ChallengeType, loader: ClassLoader?): Challenge? {
        val challenges = loadChallengesFromJSONUsing(loader)
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
