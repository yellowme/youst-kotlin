package mx.yellowme.youst.core.components.dialogs;

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Dialog interface for callers to respond back button action.
 */
interface OnDialogBackListener {
    fun onBackFromDialog()
}

/**
 * Base implementation to create a custom dialog.
 */
abstract class CommonDialog private constructor(
    context: Context,
    private val mResizePercentage: Double
) : Dialog(context), View.OnClickListener {

    //region Attributes

    protected abstract val layoutId: Int

    //endregion

    //region Setup

    internal constructor(context: Context) : this(context, 0.85)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(layoutId)
        bindViews()
        resizeDialogWindow(mResizePercentage)
    }

    protected abstract fun bindViews()

    //endregion

    //region Resizing

    /**
     * Helper to close the dialog.
     */
    internal fun hideIfVisible() {
        if (isShowing) {
            dismiss()
        }
    }

    /**
     * Resize the custom dialog given a width screen percentage.
     *
     * @param percentage of the current device screen width.
     */
    private fun resizeDialogWindow(percentage: Double) {
        val window = window
        val size = Point()
        window?.let {
            window.windowManager?.let {
                val display = it.defaultDisplay
                display.getSize(size)
                window.setLayout((size.x * percentage).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
                window.setGravity(Gravity.CENTER)
            }
        }
    }

    //endregion

}
