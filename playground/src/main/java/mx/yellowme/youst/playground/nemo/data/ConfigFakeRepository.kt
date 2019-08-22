package mx.yellowme.youst.playground.nemo.data

import android.os.Handler
import mx.yellowme.youst.playground.nemo.domain.Config

class ConfigFakeRepository {

    companion object {
        var shouldFail: Boolean = false
        var defaultConfig = Config("3.3.0", 10)
    }

    fun getData(forceUpdate: Boolean, callback: SingleItemCallback<Config>) {
        if (forceUpdate) {
            Handler().postDelayed({
                handleResponse(callback)
            }, 1200)
        } else {
            handleResponse(callback)
        }
    }

    private fun handleResponse(callback: SingleItemCallback<Config>) {
        if (shouldFail) {
            callback.onServerError("Ups! Should fail.")
        } else {
            callback.onLoad(defaultConfig)
        }
    }

}
