package mx.yellowme.youst.playground.shared

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_main.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.data.PaymentFakeRepository
import mx.yellowme.youst.playground.data.UserFakeRepository

class MainScreen : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_main

    //endregion

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: Must delegate action to another layer component (ViewModel or Presenter)

        appHero.message = getString(
            R.string.screen_main,
            UserFakeRepository.defaultConfig!!.fullName,
            PaymentFakeRepository.defaultConfig!!.alias
        )
    }

}
