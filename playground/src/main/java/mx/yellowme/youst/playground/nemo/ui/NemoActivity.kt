package mx.yellowme.youst.playground.nemo.ui

import kotlinx.android.synthetic.main.screen_login.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.nemo.data.ConfigFakeRepository
import mx.yellowme.youst.playground.nemo.data.PaymentFakeRepository
import mx.yellowme.youst.playground.nemo.data.UserFakeRepository
import mx.yellowme.youst.playground.nemo.navigation.CommonNavigator
import mx.yellowme.youst.playground.nemo.navigation.fetchers.FetchConfig
import mx.yellowme.youst.playground.nemo.navigation.fetchers.FetchOtherModel
import mx.yellowme.youst.playground.nemo.navigation.fetchers.FetchUser
import mx.yellowme.youst.playground.nemo.navigation.hooks.Navigator
import mx.yellowme.youst.playground.nemo.navigation.matchers.HasLastName

class NemoActivity : BaseActivity() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_splash

    private lateinit var navigator: Navigator

    private val navigation = CommonNavigator(this)

    //endregion

    override fun onStart() {
        super.onStart()

        setProgress(true)

        //TODO: Must delegate action to another layer component (ViewModel or Presenter)
        navigator = FetchConfig(ConfigFakeRepository(), navigation)
        navigator
            .linkWith(FetchUser(UserFakeRepository(), navigation))
            .linkWith(HasLastName(UserFakeRepository(), navigation))
            .linkWith(FetchOtherModel(PaymentFakeRepository(), navigation))
            .linkWith(object : Navigator() {
                override fun run() {
                    continueFlowIf(true)
                }

                override fun executeExit() {
                    navigation.sendToMain()
                }
            })

        navigator.run()
    }

    //region View

    fun setProgress(isActive: Boolean) {
        appHero.setProgress(isActive)
    }

    //endregion
}
