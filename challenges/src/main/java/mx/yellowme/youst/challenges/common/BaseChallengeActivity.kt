package mx.yellowme.youst.challenges.common

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.challenge_crazy_lists.*
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.core.components.dialogs.SimpleInfoDialogBuilder
import mx.yellowme.youst.challenges.data.ChallengeDataHelper
import mx.yellowme.youst.challenges.domain.Challenge
import mx.yellowme.youst.challenges.domain.ChallengeType
import mx.yellowme.youst.core.components.dialogs.DialogBuilder
import mx.yellowme.youst.core.components.dialogs.ThemeDialog
import mx.yellowme.youst.core.components.toolbar.DefaultActivityToolbarDelegate
import mx.yellowme.youst.core.components.toolbar.HelpAwareListener
import mx.yellowme.youst.core.extensions.extraStringOrThrow
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.R as coreR

/**
 * TODO: Add KDoc
 */
abstract class BaseChallengeActivity : BaseActivity(), HelpAwareListener {

    abstract val currentType: ChallengeType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActionToolbar.title = extraStringOrThrow(TOOLBAR_TITLE)
    }

    override fun didTapHelpOption() {
        ChallengeDataHelper.challengeForType(currentType, classLoader)?.let {
            DialogBuilder()
                .title(getString(R.string.instructions))
                .message(it.help)
                .actionTitle(getString(coreR.string.understood))
                .build(this)
                .show()
        } ?: toast("There was a problem loading the help for this challenge")
    }

    companion object {
        const val TOOLBAR_TITLE = "TOOLBAR_TITLE"
    }

}
