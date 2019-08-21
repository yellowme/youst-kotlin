package mx.yellowme.youst.core.components.toolbar

import android.app.Activity

class DefaultActivityToolbarDelegate(val activity: Activity) : ToolbarActionDelegate {
    override fun execute(action: ExecutableToolbarAction?) {
        when (action) {
            ExecutableToolbarAction.CLOSE -> {
                activity.onBackPressed()
            }
            ExecutableToolbarAction.HELP -> {
                (activity as? HelpAwareListener)?.didTapHelpOption()
            }
            else -> {
                /*NO-OP*/
            }
        }
    }
}
