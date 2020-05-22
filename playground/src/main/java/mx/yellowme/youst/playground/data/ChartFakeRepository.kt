package mx.yellowme.youst.playground.data

import android.os.Handler
import mx.yellowme.youst.playground.domain.ChartEntry
import kotlin.random.Random

/**
 * @author adrianleyvasanchez
 * @since 07,October,2019
 */
class ChartFakeRepository {
    companion object {
        var shouldFail: Boolean = false
        private var x = 0f
    }

    fun getData(forceUpdate: Boolean, callback: SingleItemCallback<ChartEntry>) {
        if (forceUpdate) {
            Handler().postDelayed({
                handleResponse(callback)
            }, 1200)
        } else {
            handleResponse(callback)
        }
    }

    private fun handleResponse(callback: SingleItemCallback<ChartEntry>) {
        x += 1
        val y = Random.nextInt(0, 100).toFloat()
        val z = Random.nextInt(0, 100).toFloat()

        if (shouldFail) {
            callback.onServerError("Ups! Should fail.")
        } else {
            callback.onLoad(ChartEntry(x, y, z))
        }
    }
}
