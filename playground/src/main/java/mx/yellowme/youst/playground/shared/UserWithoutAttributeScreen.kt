package mx.yellowme.youst.playground.shared

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_user_no_attribute.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener
import mx.yellowme.youst.playground.data.UserFakeRepository
import mx.yellowme.youst.playground.nemo.navigator.CommonNavigator

class UserWithoutAttributeScreen : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_user_no_attribute

    private val navigation = CommonNavigator(this)

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appHero.listener = object : AppHeroActionListener {
            override fun onClickAction() {
                //TODO: Must delegate action to another layer component (ViewModel or Presenter)
                UserFakeRepository.defaultConfig?.lastName = "Burgos"
                navigation.sendToSplash()
            }
        }
    }

    //endregion

}
