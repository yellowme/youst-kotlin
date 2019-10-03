package mx.yellowme.youst.splash

import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.splash.*
import mx.yellowme.youst.R
import mx.yellowme.youst.core.components.ThemeConstants.DARK
import mx.yellowme.youst.core.components.ThemeConstants.IS_DARK
import mx.yellowme.youst.core.components.ThemeConstants.LIGHT
import mx.yellowme.youst.core.components.ThemeConstants.STORAGE_NAME
import mx.yellowme.youst.core.components.ThemeConstants.THEME_QUERY
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
            val rawRes =
                if (getSharedPreferences(STORAGE_NAME, 0)
                        .getBoolean(IS_DARK, false)
                ) {
                    R.raw.logo_reveal_dark
                } else {
                    R.raw.logo_reveal
                }
            setAnimation(rawRes)
            useHardwareAcceleration()
            setListener {
                onAnimationEnd {
                    launch(intentTo(Activities.Dashboard), finishCaller = true)
                }
            }
        }
    }

    private fun handleUri(){
        val data: Uri? = intent?.data
        if (data?.getQueryParameter(THEME_QUERY) == DARK) {
            getSharedPreferences(STORAGE_NAME, 0).edit()
                .putBoolean(IS_DARK, true).apply()
        } else if (data?.getQueryParameter(THEME_QUERY) == LIGHT) {
            getSharedPreferences(STORAGE_NAME, 0).edit()
                .putBoolean(IS_DARK, false).apply()
        }
    }
}
