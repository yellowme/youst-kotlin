package mx.yellowme.youst.core.hooks

import android.os.SystemClock
import android.view.View

/**
 * Tired of duplicate clicks on your views? Look no further, the solution is this
 * simple class that replaces the [View.setOnClickListener] method with a simple approach that
 * wont require for you to add new third party libraries.
 *
 * You just need to call the method [View.setSingleOnClickListener] over any view providing the
 * closure to be called after the user clicks the view.
 */
class SingleOnClickListener(
        private var defaultInterval: Int = 1000,
        private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0L

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(view)
    }
}

fun View.setSingleOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SingleOnClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
