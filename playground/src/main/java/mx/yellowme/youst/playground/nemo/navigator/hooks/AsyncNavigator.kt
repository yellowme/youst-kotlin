package mx.yellowme.nemo.common.navigation.hooks

import mx.yellowme.youst.playground.data.SimpleCallback
import mx.yellowme.youst.playground.data.SingleItemCallback
import mx.yellowme.youst.playground.nemo.navigator.hooks.Navigator

abstract class AsyncSingleNavigator<Model>(
    private val handler: Navigator
) : SingleItemCallback<Model> {

    override fun onServerError(message: String) {
        handler.executeExit()
    }

}

abstract class AsyncSimpleNavigator(
    private val handler: Navigator
) : SimpleCallback {

    override fun onServerError(message: String) {
        handler.executeExit()
    }

}