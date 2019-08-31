package mx.yellowme.youst.playground.nemo.ui

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_unsupported.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.nemo.components.AppHeroActionListener
import mx.yellowme.youst.playground.nemo.data.ConfigFakeRepository
import mx.yellowme.youst.playground.domain.Config
import mx.yellowme.youst.playground.nemo.navigation.CommonNavigator

class UnsupportedVersionScreen : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_unsupported

    private val navigation = CommonNavigator(this)

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appHero.listener = object : AppHeroActionListener {
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
