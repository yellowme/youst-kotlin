package mx.yellowme.youst.common.activities;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.yellowme.youst.R

/**
 * TODO: Add docs
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * TODO: Add docs
     */
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        bindViews()
    }

    /**
     * TODO: Add docs
     */
    protected abstract fun bindViews()

    /**
     * TODO: Add docs
     */
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * TODO: Add docs
     */
    fun challengeAtWIP() {
        toast(getString(R.string.work_in_progress))
    }

}
