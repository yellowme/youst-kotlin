package mx.yellowme.youst.core.extensions

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// dp to pixels
val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

/**
 * Display given content or hide the view if the argument is null or blank.
 */
fun TextView.hideOrDisplay(content: String?, gonable: Boolean = true) {
    if (content.isNullOrBlank()) {
        visibility = if (gonable) View.GONE else View.INVISIBLE
    } else {
        visibility = View.VISIBLE
        text = content
    }
}

/**
 * Display given content or hide the view if the argument is null or blank.
 */
fun TextView.displayOrThrow(content: String?) {
    if (content.isNullOrBlank()) {
        throw RuntimeException("Content for component should not be null or empty")
    } else {
        visibility = View.VISIBLE
        text = content
    }
}

/**
 * Inflate a layout by resourceId when working with custom view components.
 */
fun ViewGroup.inflate(layoutIdRes: Int, context: Context): View {
    return LayoutInflater.from(context).inflate(layoutIdRes, this, true)
}

/**
 * Change visibility in order to disappear from the screen.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Change visibility in order to appear from the screen.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

val View.isInvisible
    get() = this.visibility == View.INVISIBLE

val View.isVisible
    get() = this.visibility == View.VISIBLE

/**
 * Change visibility in order to disappear from the screen.
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}
