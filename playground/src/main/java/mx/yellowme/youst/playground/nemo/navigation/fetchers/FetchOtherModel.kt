package mx.yellowme.youst.playground.nemo.navigation.fetchers

import mx.yellowme.nemo.common.navigation.hooks.AsyncSimpleNavigator
import mx.yellowme.youst.playground.nemo.navigation.hooks.Navigator
import mx.yellowme.youst.playground.nemo.data.PaymentFakeRepository
import mx.yellowme.youst.playground.nemo.navigation.BaseNavigator

class FetchOtherModel<Navigation>(
    private val repository: PaymentFakeRepository,
    private val navigation: Navigation
) : Navigator() where Navigation : BaseNavigator {

    override fun run() {
        repository.hasData(true, object : AsyncSimpleNavigator(this) {
            override fun onSuccess() {
                continueFlowIf(true)
            }
        })
    }

    override fun executeExit() {
        navigation.sendToMissingPaymentOption()
    }
}
