package mx.yellowme.youst.splash

import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.splash.*
import mx.yellowme.youst.R
import mx.yellowme.youst.core.components.ThemeConstants.DARK
import mx.yellowme.youst.core.components.ThemeConstants.LIGHT
import mx.yellowme.youst.core.components.ThemeConstants.THEME_QUERY
import mx.yellowme.youst.core.components.isDarkThemeEnabled
import mx.yellowme.youst.core.components.setDarkThemeEnabled
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.animations.setListener
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.intentTo

class SplashActivity : BaseActivity() {

    override val layoutId = R.layout.splash

    override fun onCreate(savedInstanceState: Bundle?) {
        handleUri()
        super.onCreate(savedInstanceState)
        splashAnimationView?.apply {
            setAnimation(if (isDarkThemeEnabled()) R.raw.logo_reveal_dark else R.raw.logo_reveal)
            useHardwareAcceleration()
            setListener {
                onAnimationEnd {
                    launch(intentTo(Activities.Dashboard), finishCaller = true)
                }
            }
        }
    }

    private fun handleUri() {
        val data: Uri? = intent?.data
        val isDark = when (data?.getQueryParameter(THEME_QUERY)) {
            DARK -> true
            LIGHT -> false
            else -> null
        } ?: return
        setDarkThemeEnabled(isDark)
    }
}
