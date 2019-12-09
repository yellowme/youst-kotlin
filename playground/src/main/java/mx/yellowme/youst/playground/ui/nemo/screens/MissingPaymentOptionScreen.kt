package mx.yellowme.youst.playground.ui.nemo.screens

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_no_payment_option.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener
import mx.yellowme.youst.playground.data.PaymentFakeRepository
import mx.yellowme.youst.playground.domain.Payment
import mx.yellowme.youst.playground.ui.nemo.navigator.CommonActivityHelm

class MissingPaymentOptionScreen : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_no_payment_option

    private val navigation = CommonActivityHelm(this)

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appHero.mainActionListener = object : AppHeroActionListener {
            override fun onClickAction() {
                //TODO: Must delegate action to another layer component (ViewModel or Presenter)
                PaymentFakeRepository.defaultConfig =
                    Payment("Enso", "4242424242424242")
                navigation.sendToSplash()
            }
        }
    }

    //endregion

}
