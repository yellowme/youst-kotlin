package mx.yellowme.youst.core.components.toolbar

interface HelpAwareListener {
    fun didTapHelpOption()
}

interface ToolbarActionDelegate {
    fun execute(action: ExecutableToolbarAction?)
}
