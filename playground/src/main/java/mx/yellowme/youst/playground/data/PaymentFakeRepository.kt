package mx.yellowme.youst.playground.data

import android.os.Handler
import mx.yellowme.youst.playground.domain.Payment

class PaymentFakeRepository {

    companion object {
        var shouldFail: Boolean = false
        var defaultConfig: Payment? = null
    }

    fun hasData(forceUpdate: Boolean, callback: SimpleCallback) {
        if (forceUpdate) {
            Handler().postDelayed({
                handleResponse(callback)
            }, 1200)
        } else {
            handleResponse(callback)
        }
    }

    fun getData(forceUpdate: Boolean, callback: SingleItemCallback<Payment>) {
        if (forceUpdate) {
            Handler().postDelayed({
                handleResponse(callback)
            }, 1200)
        } else {
            handleResponse(callback)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponse(callback: ServerCallback) {
        defaultConfig?.let {
            if (shouldFail) {
                callback.onServerError("Ups! Should fail.")
            } else {
                (callback as? SingleItemCallback<Payment>)?.onLoad(it)

                (callback as? SimpleCallback)?.onSuccess()
            }
        } ?: callback.onServerError("Ups! No payment method saved.")
    }

}
