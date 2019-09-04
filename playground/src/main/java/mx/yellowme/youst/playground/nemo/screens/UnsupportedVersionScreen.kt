package mx.yellowme.youst.playground.nemo.screens

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_unsupported.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener
import mx.yellowme.youst.playground.data.ConfigFakeRepository
import mx.yellowme.youst.playground.domain.Config
import mx.yellowme.youst.playground.nemo.navigator.CommonNavigator

class UnsupportedVersionScreen : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_unsupported

    private val navigation = CommonNavigator(this)

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appHero.mainActionListener = object : AppHeroActionListener {
            override fun onClickAction() {
                //TODO: Must delegate action to another layer component (ViewModel or Presenter)
                ConfigFakeRepository.defaultConfig =
                    Config("3.3.12", 12)
                navigation.sendToSplash()
            }
        }
    }

    //endregion

}
