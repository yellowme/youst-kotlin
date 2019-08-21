package mx.yellowme.youst.core.components.toolbar

import mx.yellowme.youst.core.R

sealed class ToolbarAction {
    object None : ToolbarAction()

    sealed class LeftToolbarAction : ToolbarAction() {
        object Back : LeftToolbarAction()
    }

    sealed class MenuToolbarAction : ToolbarAction() {
        object Help : MenuToolbarAction()
    }

    companion object {
        fun with(name: String): ToolbarAction {
            return when (name.toUpperCase()) {
                "NONE" -> None
                "BACK" -> LeftToolbarAction.Back
                "HELP" -> MenuToolbarAction.Help
                else -> throw RuntimeException("Unsupported toolbar action named: $name")
            }
        }
    }

    val resId: Int?
        get() {
            return when (this) {
                is None -> {
                    null
                }
                is LeftToolbarAction.Back -> {
                    R.drawable.ic_back_black
                }
                is MenuToolbarAction.Help -> {
                    R.menu.menu_help
                }
            }
        }

    fun executableAction(forResId: Int): ExecutableToolbarAction? {
        return when (forResId) {
            R.id.action_help -> {
                ExecutableToolbarAction.HELP
            }
            R.drawable.ic_back_black -> {
                ExecutableToolbarAction.CLOSE
            }
            else -> throw RuntimeException("The id: $forResId provided is not valid")
        }
    }

}

enum class ExecutableToolbarAction {
    CLOSE, HELP;

    fun isClose(): Boolean {
        return this == CLOSE
    }
}
