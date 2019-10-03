package mx.yellowme.youst.core.hooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.components.SharedApp
import mx.yellowme.youst.core.components.ThemeConstants
import mx.yellowme.youst.core.extensions.getThemeName

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getSharedPreferences(
                ThemeConstants.STORAGE_NAME,
                0
            ).getBoolean(ThemeConstants.IS_DARK, false)
        ) {
            replaceTheme(getThemeName())
        }
        setContentView(layoutId)
    }

    //endregion

    private fun replaceTheme(themeName: Int) {
        when (themeName) {
            SharedApp.THEME -> {
                setTheme(R.style.SharedAppTheme_Dark)
            }
            SharedApp.THEME_DARK -> {
                setTheme(R.style.SharedAppTheme)
            }
            SharedApp.THEME_NO_ACTION_BAR -> {
                setTheme(R.style.SharedAppTheme_NoActionBar_Dark)
            }
            SharedApp.THEME_NO_ACTION_BAR_DARK -> {
                setTheme(R.style.SharedAppTheme_NoActionBar)
            }
            else -> {
            }
        }
    }
}