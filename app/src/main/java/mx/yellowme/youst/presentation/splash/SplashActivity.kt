package mx.yellowme.youst.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import mx.yellowme.youst.R
import mx.yellowme.youst.common.utils.setListener
import mx.yellowme.youst.presentation.showcase.ShowcaseActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashAnimation?.apply {
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
