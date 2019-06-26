package mx.yellowme.youst.presentation.splash

import android.content.Intent
import com.airbnb.lottie.LottieAnimationView
import mx.yellowme.youst.R
import mx.yellowme.youst.common.activities.BaseActivity
import mx.yellowme.youst.common.utils.setListener
import mx.yellowme.youst.presentation.showcase.ShowcaseActivity

class SplashActivity : BaseActivity() {

    private lateinit var splashAnimationView: LottieAnimationView

    override val layoutResource: Int
        get() = R.layout.activity_splash

    override fun bindViews() {
        splashAnimationView = findViewById<LottieAnimationView>(R.id.splashAnimationView).apply {
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
