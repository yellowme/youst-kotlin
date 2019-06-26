package mx.yellowme.youst.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.yellowme.youst.R
import mx.yellowme.youst.presentation.showcase.ShowcaseActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SharedAppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Starts the activity after animations
        val intent = Intent(this, ShowcaseActivity::class.java)
        startActivity(intent)
        finish()
    }
}
