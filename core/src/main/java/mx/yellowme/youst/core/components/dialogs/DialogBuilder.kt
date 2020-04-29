package mx.yellowme.youst.core.components.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import mx.yellowme.youst.core.R.style.DialogTheme

/**
 * TODO: Complete docs
 *
 */
class DialogBuilder {
    private var dialogTitle: String? = null
    private var message: String? = null

    private var actionTitle: String? = null
    private var actionListener: ((dialog: DialogInterface, which: Int) -> Unit)? = null

    private var defaultSelectedItem: Int = 0
    private var items: Array<String>? = null
    private var itemsListener: ((dialog: DialogInterface, which: Int) -> Unit)? = null

    fun title(dialogTitle: String?): DialogBuilder {
        this.dialogTitle = dialogTitle
        return this
    }

    fun message(message: String?): DialogBuilder {
        this.message = message
        return this
    }

    fun actionTitle(actionTitle: String?): DialogBuilder {
        this.actionTitle = actionTitle
        return this
    }

    fun items(
        items: Array<String>,
        defaultSelectedItem: Int,
        listener: (dialog: DialogInterface, which: Int) -> Unit
    ): DialogBuilder {
        this.items = items
        this.defaultSelectedItem = defaultSelectedItem
        this.itemsListener = listener
        return this
    }

    fun build(withContext: Context): MaterialAlertDialogBuilder {
        return MaterialAlertDialogBuilder(withContext, DialogTheme)
            .setTitle(dialogTitle)
            .setMessage(message)
            .setPositiveButton(actionTitle, actionListener)
            .setSingleChoiceItems(items, defaultSelectedItem, itemsListener)
            .setItems(items, itemsListener)
    }
}