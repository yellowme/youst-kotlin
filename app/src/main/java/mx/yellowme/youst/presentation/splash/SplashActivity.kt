package mx.yellowme.youst.presentation.splash

import android.content.Intent
import kotlinx.android.synthetic.main.activity_splash.*
import mx.yellowme.youst.R
import mx.yellowme.youst.common.activities.BaseActivity
import mx.yellowme.youst.common.utils.setListener
import mx.yellowme.youst.presentation.showcase.ShowcaseActivity

class SplashActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_splash

    //TODO: Remove method from parent class
    override fun bindViews() {
        splashAnimationView?.apply {
            useHardwareAcceleration()
            setListener {
                onAnimationEnd {
                    val intent = Intent(this@SplashActivity, ShowcaseActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}
