package mx.yellowme.youst.challenges

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import mx.yellowme.youst.R
import mx.yellowme.youst.core.components.dialogs.SimpleInfoDialogBuilder
import mx.yellowme.youst.core.domain.Challenge
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.data.ChallengeDataHelper
import mx.yellowme.youst.core.utils.findOrThrow

/**
 * TODO: Add javadoc
 */
abstract class BaseChallengeActivity : BaseActivity() {

    private var toolbarAction: Toolbar? = null
    private var toolbarTitle: TextView? = null
    private var leftAction: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarAction = findOrThrow(R.id.toolbarAction)
        toolbarTitle = findOrThrow(R.id.toolbarTitle)

        leftAction = findOrThrow(R.id.leftAction)
        leftAction?.setOnClickListener { _ -> finish() }

        setSupportActionBar(toolbarAction)
        setupTitleIfNeeded()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_challenge_help, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_help) {
            (this as? MenuActionsListener)?.onClickHelpMenu()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * TODO: Add docs
     */
    interface MenuActionsListener {
        fun onClickHelpMenu()
    }

    protected fun dialogWithHelpFor(type: Challenge.ChallengeType) {
        ChallengeDataHelper.challengeForId(type, classLoader)?.let {
            SimpleInfoDialogBuilder()
                .title(getString(R.string.instructions))
                .message(it.help)
                .actionTitle(getString(R.string.understood))
                .build(this)
                .show()
        } ?: toast("There was a problem loading the help for this challenge")
    }

    /**
     * TODO: Add docs
     */
    private fun setupTitleIfNeeded() {
        intent.extras?.let {
            if (it.containsKey(TOOLBAR_TITLE)) {
                toolbarTitle?.text = it.getString(TOOLBAR_TITLE)
            }
        }
    }

    companion object {
        const val TOOLBAR_TITLE = "TOOLBAR_TITLE"
    }

}
