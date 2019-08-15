package mx.yellowme.youst.core.components.dialogs

import android.app.Activity
import android.view.View
import android.view.View.GONE
import kotlinx.android.synthetic.main.component_simple_info_dialog.*
import mx.yellowme.youst.core.R

/**
 * Displays a custom dialog with a title, description and
 * a back button, all elements are customizable.
 */
abstract class SimpleInfoDialog internal constructor(source: Activity) : CommonDialog(source),
    View.OnClickListener {

    //region Decoration Attributes

    protected abstract val title: String?
    protected abstract val bodyMessage: String?
    protected abstract val backActionTitle: String?

    private var mListener: OnDialogBackListener? = null

    //endregion

    init {
        if (source is OnDialogBackListener) {
            mListener = source
        }
    }

    override val layoutId: Int = R.layout.component_simple_info_dialog

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
