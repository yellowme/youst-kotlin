package mx.yellowme.youst.playground.nemo.navigator.fetchers

import mx.yellowme.youst.playground.nemo.navigator.hooks.AsyncSimpleNavigator
import mx.yellowme.youst.playground.nemo.navigator.hooks.Navigator
import mx.yellowme.youst.playground.data.PaymentFakeRepository
import mx.yellowme.youst.playground.nemo.navigator.BaseHelm

class FetchOtherModel<Navigation>(
    private val repository: PaymentFakeRepository,
    private val navigation: Navigation
) : Navigator() where Navigation : BaseHelm {

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
