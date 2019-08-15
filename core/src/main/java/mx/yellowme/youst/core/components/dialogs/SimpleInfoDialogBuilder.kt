package mx.yellowme.youst.core.components.dialogs

import android.app.Activity

/**
 * TODO: Add docs
 */
class SimpleInfoDialogBuilder {

    private var dialogTitle: String? = null
    private var message: String? = null
    private var actionTitle: String? = null

    fun title(dialogTitle: String?): SimpleInfoDialogBuilder {
        this.dialogTitle = dialogTitle
        return this
    }

    fun message(message: String?): SimpleInfoDialogBuilder {
        this.message = message
        return this
    }

    fun actionTitle(actionTitle: String?): SimpleInfoDialogBuilder {
        this.actionTitle = actionTitle
        return this
    }

    fun build(forActivity: Activity): SimpleInfoDialog {
        return object : SimpleInfoDialog(forActivity) {
            override val title: String?
                get() = dialogTitle

            override val bodyMessage: String?
                get() = message

            override val backActionTitle: String?
                get() = actionTitle
        }
    }

}
