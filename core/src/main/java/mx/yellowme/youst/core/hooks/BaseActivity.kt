package mx.yellowme.youst.core.hooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.yellowme.youst.core.components.useSelectedTheme

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useSelectedTheme()
        setContentView(layoutId)
    }

    //endregion

}
