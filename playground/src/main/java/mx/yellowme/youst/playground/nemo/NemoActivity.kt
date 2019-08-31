package mx.yellowme.youst.playground.nemo

import kotlinx.android.synthetic.main.screen_login.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.data.ConfigFakeRepository
import mx.yellowme.youst.playground.data.PaymentFakeRepository
import mx.yellowme.youst.playground.data.UserFakeRepository
import mx.yellowme.youst.playground.nemo.navigator.CommonNavigator
import mx.yellowme.youst.playground.nemo.navigator.fetchers.FetchConfig
import mx.yellowme.youst.playground.nemo.navigator.fetchers.FetchOtherModel
import mx.yellowme.youst.playground.nemo.navigator.fetchers.FetchUser
import mx.yellowme.youst.playground.nemo.navigator.hooks.Navigator
import mx.yellowme.youst.playground.nemo.navigator.matchers.HasLastName

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

    private fun setProgress(isActive: Boolean) {
        appHero.setProgress(isActive)
    }

    //endregion
}
