package mx.yellowme.youst.playground.nemo.data

import android.os.Handler
import mx.yellowme.youst.playground.nemo.domain.User

class UserFakeRepository {

    companion object {
        var shouldFail: Boolean = false
        var defaultConfig: User? = null
    }

    fun getData(forceUpdate: Boolean, callback: SingleItemCallback<User>) {
        if (forceUpdate) {
            Handler().postDelayed({
                handleResponse(callback)
            }, 1200)
        } else {
            handleResponse(callback)
        }
    }

    private fun handleResponse(callback: SingleItemCallback<User>) {
        defaultConfig?.let {
            if (shouldFail) {
                callback.onServerError("Ups! Should fail.")
            } else {
                callback.onLoad(it)
            }
        } ?: callback.onServerError("Ups! No user session saved locally.")
    }

}
