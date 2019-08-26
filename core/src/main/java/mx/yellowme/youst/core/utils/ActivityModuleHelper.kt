package mx.yellowme.youst.core.utils

import android.content.Intent

/**
 * Helpers to start activities in a modularized world.
 */

private const val PACKAGE_NAME = "mx.yellowme.youst"

/**
 * Create an Intent with [Intent.ACTION_VIEW] to an [AddressableActivity].
 */
fun intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        addressableActivity.className
    )
}

/**
 * An [android.app.Activity] that can be addressed by an intent.
 *
 * ## Example
 *
 * ```
 * object Challenges {
 *      object CrazyLists : AddressableActivity {
 *          override val className = "$PACKAGE_NAME.challenges.CrazyListsChallengeActivity"
 *          const val SOME_EXTRA_ID = "SOME_EXTRA_ID"
 *          const val SOME_RESULT_EXTRA_ID = "SOME_RESULT_EXTRA_ID ="
 *      }
 * }
 * ```
 */
interface AddressableActivity {
    /**
     * The activity class name.
     */
    val className: String
}

/**
 * All addressable activities.
 *
 * Can contain intent extra names or functions associated with the activity creation.
 */
object Activities {

    object Dashboard : AddressableActivity {
        override val className = "$PACKAGE_NAME.dashboard.DashboardActivity"
    }

    object Playground : AddressableActivity {
        override val className = "$PACKAGE_NAME.playground.PlaygroundActivity"

        object Navigation : AddressableActivity {
            override val className = "$PACKAGE_NAME.playground.navigation.NavigationActivity"
        }

        object Nemo : AddressableActivity {
            override val className = "$PACKAGE_NAME.playground.nemo.ui.NemoActivity"
        }
    }

    object Challenges : AddressableActivity {

        override val className = "$PACKAGE_NAME.challenges.ChallengesActivity"

        object CrazyLists : AddressableActivity {
            override val className = "$PACKAGE_NAME.challenges.CrazyListsChallengeActivity"
        }

        object ListenToMe : AddressableActivity {
            override val className = "$PACKAGE_NAME.challenges.ListenToMeChallengeActivity"
        }
    }

    object MiniApps : AddressableActivity {
        override val className = "$PACKAGE_NAME.miniapps.MiniAppsActivity"

        object Chords : AddressableActivity {
            override val className = "$PACKAGE_NAME.miniapps.ui.ChordsActivity"
        }
    }
}
