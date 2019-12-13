package mx.yellowme.youst.playground.ui.camerax.utils

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit

/**
 * @author adrianleyvasanchez
 * @since 13,December,2019
 */
class LuminosityAnalyzer : ImageAnalysis.Analyzer {

    //region Attributes

    private var lastAnalyzedTimestamp = 0L

    //endregion

    //region Helpers

    override fun analyze(image: ImageProxy?, rotationDegrees: Int) {
        val currentTimestamp = System.currentTimeMillis()
        if (currentTimestamp - lastAnalyzedTimestamp >=
            TimeUnit.SECONDS.toMillis(1)) {
            val buffer = image!!.planes[0].buffer
            val data = buffer.toByteArray()
            val pixels = data.map { it.toInt() and 0xFF }
            val luma = pixels.average()
            Log.d("CameraXApp", "Average luminosity: $luma")
            lastAnalyzedTimestamp = currentTimestamp
        }
    }

    //endregion
}

/**
 * Helper extension function used to extract a byte array from an
 * image plane buffer
 */
private fun ByteBuffer.toByteArray(): ByteArray {
    rewind()
    val data = ByteArray(remaining())
    get(data)
    return data
}