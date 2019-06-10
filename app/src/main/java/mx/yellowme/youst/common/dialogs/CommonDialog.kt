package mx.yellowme.youst.common.dialogs;

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Base implementation to create a custom dialog.
 *
 *
 * Created by luisburgos.
 */
abstract class CommonDialog private constructor(
    context: Context,
    private val mResizePercentage: Double
) : Dialog(context), View.OnClickListener {

    // CONCRETE DECORATION

    protected abstract val layoutId: Int

    internal constructor(context: Context) : this(context, 0.85)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(layoutId)
        bindViews()
        resizeDialogWindow(mResizePercentage)
    }

    /**
     * Dialog interface for callers to respond back button action.
     */
    interface OnBackListener {
        fun onBackFromDialog()
    }

    protected abstract fun bindViews()

    // RESIZING

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

}
