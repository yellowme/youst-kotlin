package mx.yellowme.youst.playground.nemo.navigation.fetchers

import mx.yellowme.nemo.common.navigation.hooks.AsyncSingleNavigator
import mx.yellowme.youst.playground.nemo.navigation.hooks.Navigator
import mx.yellowme.youst.playground.nemo.navigation.BaseNavigator
import mx.yellowme.youst.playground.nemo.data.ConfigFakeRepository
import mx.yellowme.youst.playground.nemo.domain.Config

class FetchConfig<Navigation>(
    private val repository: ConfigFakeRepository,
    private val navigator: Navigation
) : Navigator() where Navigation : BaseNavigator {

    override fun run() {
        repository.getData(true, object : AsyncSingleNavigator<Config>(this) {
            override fun onLoad(item: Config?) {
                continueFlowIf(item?.hasSupportedVersion ?: false)
            }
        })
    }

    override fun executeExit() {
        navigator.sendToUnsupportedVersion()
    }

}
