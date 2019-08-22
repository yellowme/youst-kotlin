package mx.yellowme.youst.playground.nemo.ui

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_login.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.nemo.components.AppHeroActionListener
import mx.yellowme.youst.playground.nemo.data.UserFakeRepository
import mx.yellowme.youst.playground.nemo.domain.User
import mx.yellowme.youst.playground.nemo.navigation.CommonNavigator

class LoginScreen : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_login

    private val navigation = CommonNavigator(this)

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appHero.listener = object : AppHeroActionListener {
            override fun onClickAction() {
                //TODO: Must delegate action to another layer component (ViewModel or Presenter)
                UserFakeRepository.defaultConfig = User("Luis")
                navigation.sendToSplash()
            }
        }
    }

    //endregion

    //region View

    fun setProgress(isActive: Boolean) {
        appHero.setProgress(isActive)
    }

    //endregion
}
