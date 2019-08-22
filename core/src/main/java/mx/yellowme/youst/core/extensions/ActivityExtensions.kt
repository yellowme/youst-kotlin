package mx.yellowme.youst.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import mx.yellowme.youst.core.R

//region Start Activity

/**
 * Example usage:
 *
 * ```
 * // Simple Activities
 * launch<MyActivity>()
 * launch<MyActivity>(finishCaller = true)
 *
 * // Add Intent extras
 * launch<MyActivity> {
 *      putExtra(INTENT_EXTRA_ID, model.someAttribute)
 * }
 *
 * // Add custom flags
 * launch<MyActivity> {
 *      putExtra(INTENT_EXTRA_ID, model.someAttribute)
 *      addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
 * }
 *
 * // Add Shared Transitions
 * val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, someViewRef, "someViewKey")
 * launch<MyActivity>(options = options) {
 *      putExtra(INTENT_EXTRA_ID, model.someAttribute)
 * }
 * ```
 */
inline fun <reified T : Any> Activity.launch(
    finishCaller: Boolean = false,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    launch(intent, finishCaller, options, init)
}

/**
 * Example usage:
 *
 * ```
 * // Simple Activities
 * launch(MyActivity::class.java)
 * launch(MyActivity::class.java, finishCaller = true)
 *
 * // Add Intent extras
 * launch(MyActivity::class.java) {
 *      putExtra(INTENT_EXTRA_ID, model.someAttribute)
 * }
 *
 * // Add custom flags
 * launch(MyActivity::class.java) {
 *      putExtra(INTENT_EXTRA_ID, model.someAttribute)
 *      addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
 * }
 *
 * // Add Shared Transitions
 * val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, someViewRef, "someViewKey")
 * launch(MyActivity::class.java, options = options) {
 *      putExtra(INTENT_EXTRA_ID, model.someAttribute)
 * }
 * ```
 */
fun Activity.launch(
    toClass: Class<*>,
    finishCaller: Boolean = false,
    options: Bundle? = null,
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent(this, toClass)
    launch(intent, finishCaller, options, init)
}

/**
 * Example usage:
 *
 * ```
 * // Custom class
 * val intent = newIntent(this, toClass)
 * launch(intent, finishCaller, options, init)
 *
 * // Reified class
 * val intent = newIntent<T>(this)
 * launch(intent, finishCaller, options, init)
 * ```
 */
fun Activity.launch(
    intent: Intent,
    finishCaller: Boolean = false,
    options: Bundle? = null,
    init: Intent.() -> Unit = {}
) {
    intent.init()
    startActivity(intent, options)
    if (finishCaller) {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}

//endregion

//region Start Activity for Result

/**
 * Example usage:
 *
 * ```
 * launchActivity<UserDetailActivity>(requestCode = 1234) {
 *      putExtra(INTENT_USER_ID, user.id)
 * }
 * ```
 */
inline fun <reified T : Any> Activity.launch(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()

    startActivityForResult(intent, requestCode, options)
}

//endregion

//region Intent

fun <T : Any> newIntent(context: Context, toClass: Class<T>): Intent = Intent(context, toClass)

inline fun <reified T : Any> newIntent(context: Context): Intent = newIntent(context, T::class.java)

//endregion
