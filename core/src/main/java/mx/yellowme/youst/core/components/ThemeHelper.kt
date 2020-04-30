package mx.yellowme.youst.core.components

import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import mx.yellowme.youst.core.components.ThemeConstants.THEME_PREFERENCES
import mx.yellowme.youst.core.components.ThemeConstants.SELECTED_THEME
import mx.yellowme.youst.core.components.dialogs.DialogBuilder
import mx.yellowme.youst.core.extensions.*

object ThemeConstants {
    const val THEME_PREFERENCES = "THEME_PREFERENCES"
    const val SELECTED_THEME = "SELECTED_THEME"
}

fun AppCompatActivity.isDarkThemeEnabled(): Boolean {
    val systemIsDark =
        (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    return when (getSelectedTheme()) {
        Theme.DARK -> true
        Theme.LIGHT -> false
        Theme.FOLLOW_SYSTEM -> systemIsDark
    }
}

fun AppCompatActivity.useSelectedTheme() {
    AppCompatDelegate.setDefaultNightMode(getSelectedTheme().value)
}

fun AppCompatActivity.getSelectedTheme(): Theme {
    return Theme.valueOf(getStringFrom(THEME_PREFERENCES, SELECTED_THEME, Theme.FOLLOW_SYSTEM.name))
}

fun AppCompatActivity.setTheme(theme: Theme) {
    saveStringTo(THEME_PREFERENCES, SELECTED_THEME, theme.name)
}

fun AppCompatActivity.showThemeChooser() {
    val themesNames = arrayOf(Theme.LIGHT.title, Theme.DARK.title, Theme.FOLLOW_SYSTEM.title)
    val checkedItem = getSelectedTheme().ordinal

    DialogBuilder()
        .title("Theme")
        .items(themesNames, checkedItem) { dialog: DialogInterface, selected: Int ->
            val selectedTheme = Theme.values()[selected]
            setTheme(selectedTheme)
            AppCompatDelegate.setDefaultNightMode(selectedTheme.value)
            dialog.dismiss()
        }
        .build(this)
        .show()
}

enum class Theme(val title: String, val value: Int) {
    LIGHT("Light mode", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("Dark mode", AppCompatDelegate.MODE_NIGHT_YES),
    FOLLOW_SYSTEM("Follow the system", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
}
