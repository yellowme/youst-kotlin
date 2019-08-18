package mx.yellowme.youst.splash

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_splash.*
import mx.yellowme.youst.R
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.animations.setListener
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.intentTo

class SplashActivity : BaseActivity() {

    override val layoutId = R.layout.screen_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashAnimationView?.apply {
            useHardwareAcceleration()
            setListener {
                onAnimationEnd {
                    launch(intentTo(Activities.Showcase), finishCaller = true)
                }
            }
        }
    }

}
