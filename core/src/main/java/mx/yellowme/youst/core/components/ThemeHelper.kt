package mx.yellowme.youst.core.components

import androidx.appcompat.app.AppCompatActivity
import mx.yellowme.youst.core.components.ThemeConstants.IS_DARK
import mx.yellowme.youst.core.components.ThemeConstants.THEME_PREFERENCES
import mx.yellowme.youst.core.extensions.getBooleanFrom
import mx.yellowme.youst.core.extensions.saveBooleanTo

object ThemeConstants {
    const val THEME_PREFERENCES = "THEME_PREFERENCES"
    const val IS_DARK = "IS_DARK"
    const val LIGHT = "light"
    const val DARK = "dark"
    const val QUERY_PARAM_STYLE = "style"
}

fun AppCompatActivity.isDarkThemeEnabled(): Boolean {
    return getBooleanFrom(THEME_PREFERENCES, IS_DARK)
}

fun AppCompatActivity.setDarkThemeEnabled(isDark: Boolean) {
    saveBooleanTo(THEME_PREFERENCES, IS_DARK, isDark)
}
