package mx.yellowme.youst.core.hooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.components.ThemeConstants
import mx.yellowme.youst.core.extensions.getBooleanFrom
import mx.yellowme.youst.core.extensions.themeId

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
            replaceTheme(themeId())
        }
        setContentView(layoutId)
    }

    //endregion

    private fun replaceTheme(themeName: Int) {
        when (themeName) {
            R.id.theme -> {
                setTheme(R.style.SharedAppThemeDark)
            }
            R.id.theme_no_action_bar -> {
                setTheme(R.style.SharedAppThemeDark_NoActionBar)
            }
        }
    }
}