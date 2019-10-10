package mx.yellowme.youst.core.hooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.components.SharedApp
import mx.yellowme.youst.core.components.ThemeConstants
import mx.yellowme.youst.core.extensions.getBooleanFrom
import mx.yellowme.youst.core.extensions.getThemeName

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getBooleanFrom(
                ThemeConstants.THEME_PREFERENCES,
                ThemeConstants.IS_DARK
            )
        ) {
            replaceTheme(getThemeName())
        }
        setContentView(layoutId)
    }

    //endregiono

    private fun replaceTheme(themeName: Int) {
        when (themeName) {
            R.id.theme -> {
                setTheme(R.style.SharedAppTheme_Dark)
            }
            R.id.theme_no_action_bar -> {
                setTheme(R.style.SharedAppTheme_NoActionBar_Dark)
            }
        }
    }
}