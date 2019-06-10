package mx.yellowme.youst.common.dialogs;

import android.app.Activity
import android.view.View
import android.view.View.GONE
import kotlinx.android.synthetic.main.component_simple_info_dialog.*
import mx.yellowme.youst.R

/**
 * Displays a custom dialog with a title, description and
 * a back button, all elements are customizable.
 *
 * Created by luisburgos.
 */
abstract class SimpleInfoDialog internal constructor(
    activity: Activity
) : CommonDialog(activity), View.OnClickListener {

    private var mListener: OnBackListener? = null

    override val layoutId: Int
        get() = R.layout.component_simple_info_dialog

    // CONCRETE DECORATION

    protected abstract val title: String?
    protected abstract val bodyMessage: String?
    protected abstract val backActionTitle: String?

    init {
        if (activity is OnBackListener) {
            mListener = activity
        }
    }

    override fun bindViews() {
        dialogTitle?.text = title
        title ?: run {
            dialogTitle?.visibility = GONE
        }

        dialogMessage?.text = bodyMessage
        bodyMessage ?: run {
            dialogMessage?.visibility = GONE
        }

        dialogDoneButton?.setOnClickListener(this)
        dialogDoneButton?.text = backActionTitle
    }

    override fun onClick(view: View) {
        hideIfVisible()
        mListener?.let {
            if (view.id == R.id.dialogDoneButton) {
                it.onBackFromDialog()
            }
        }
    }

}
