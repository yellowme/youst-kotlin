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
 * object Dribbble {
 *      object Shot : AddressableActivity {
 *          override val className = "$PACKAGE_NAME.dribbble.ui.shot.ShotActivity"
 *          const val EXTRA_SHOT_ID = "EXTRA_SHOT_ID"
 *          const val RESULT_EXTRA_SHOT_ID = "RESULT_EXTRA_SHOT_ID"
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

    /**
     * AboutActivity
     */
    object Showcase : AddressableActivity {
        override val className = "$PACKAGE_NAME.showcase.ui.ShowcaseActivity"
    }

    /**
     * Base object for DesignerNews activities.
     */
    object Challenges {
        /**
         * DesignerNews LoginActivity
         */
        object CrazyLists : AddressableActivity {
            override val className = "$PACKAGE_NAME.challenges.ui.CrazyListsChallengeActivity"
        }

        /**
         * DesignerNewsStory Activity
         */
        object ListenToMe : AddressableActivity {
            override val className = "$PACKAGE_NAME.challenges.ui.ListenToMeChallengeActivity"
        }
    }

}