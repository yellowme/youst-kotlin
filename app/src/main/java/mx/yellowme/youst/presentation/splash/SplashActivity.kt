package mx.yellowme.youst.presentation.splash

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*
import mx.yellowme.youst.R
import mx.yellowme.youst.common.utils.setListener
import mx.yellowme.youst.core.extensions.launchAndFinish
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.presentation.showcase.ShowcaseActivity

class SplashActivity : BaseActivity() {

    override val layoutId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashAnimationView?.apply {
            useHardwareAcceleration()
            setListener {
                onAnimationEnd {
                    launchAndFinish<ShowcaseActivity>()
                }
            }
        }
    }

}
