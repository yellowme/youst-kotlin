package mx.yellowme.youst.core.components.toolbar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.component_main_action_toolbar.view.*
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.extensions.*

/**
 * TODO: Document
 */
class MainActionToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var title: String? = null
        set(value) {
            field = value
            toolbarTitle.hideOrDisplay(title)
        }

    var delegate: ToolbarActionDelegate? = null

    private var leftAction: ToolbarAction? = null
        set(value) {
            field = value
            leftAction?.resId?.let { actionRes ->
                toolbarAction.setNavigationIcon(actionRes)
            } ?: run {
                toolbarAction.navigationIcon = null
            }
        }

    private var rightAction: ToolbarAction? = null
        set(value) {
            field = value
            rightAction?.resId?.let { menuId ->
                toolbarAction.inflateMenu(menuId)
                toolbarAction.setOnMenuItemClickListener {
                    rightAction?.executableAction(it.itemId)?.let { action ->
                        delegate?.execute(action)
                        true
                    }
                    false
                }
            }
        }

    private var displayShadowOnCollapse: Boolean = false
        set(value) {
            field = value
            if (displayShadowOnCollapse) {
                toolbarShadowView.visible()
            } else {
                toolbarShadowView.gone()
            }
        }

    init {
        inflate(R.layout.component_main_action_toolbar, context)

        attrs?.asTypeArray(context, R.styleable.MainActionToolbar)?.apply {
            title = getString(R.styleable.MainActionToolbar_toolbarTitle)

            getString(R.styleable.MainActionToolbar_leftAction)?.let {
                leftAction = ToolbarAction.with(it)
            }

            getString(R.styleable.MainActionToolbar_menuAction)?.let {
                rightAction = ToolbarAction.with(it)
            }

            displayShadowOnCollapse = getBoolean(
                R.styleable.MainActionToolbar_displayShadowOnCollapse, false
            )

            recycle()
        }

        toolbarAction?.setNavigationOnClickListener {
            leftAction?.resId?.let {
                delegate?.execute(leftAction?.executableAction(it))
            }
        }

        (context as? Activity)?.let {
            delegate = DefaultActivityToolbarDelegate(it)
        }
    }

}

