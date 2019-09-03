package mx.yellowme.youst.playground.nemo.navigator.hooks

import mx.yellowme.youst.playground.data.SimpleCallback
import mx.yellowme.youst.playground.data.SingleItemCallback

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
