package mx.yellowme.youst.splash

import android.os.Bundle
import kotlinx.android.synthetic.main.splash.splashAnimationView
import mx.yellowme.youst.R
import mx.yellowme.youst.core.components.isDarkThemeEnabled
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.animations.setListener
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.intentTo

class SplashActivity : BaseActivity() {
    override val layoutId = R.layout.splash

    override fun onCreate(savedInstanceState: Bundle?) {
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
}
