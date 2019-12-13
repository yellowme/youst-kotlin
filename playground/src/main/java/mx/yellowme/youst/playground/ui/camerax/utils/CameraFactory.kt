package mx.yellowme.youst.playground.ui.camerax.utils

import android.util.Size
import androidx.camera.core.*
import java.util.concurrent.ExecutorService

/**
 * @author adrianleyvasanchez
 * @since 13,December,2019
 */
object CameraFactory {

    fun createPreviewWithConfig(): Preview {
        val configuration = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()
        return Preview(configuration)
    }

    fun createImageCaptureWithConfig(): ImageCapture {
        val configuration = ImageCaptureConfig.Builder().apply {
            setCaptureMode(ImageCapture.CaptureMode.MAX_QUALITY)
        }.build()
        return ImageCapture(configuration)
    }

    fun createImageAnalysisWithConfig(
        executor: ExecutorService
    ): ImageAnalysis {
        val configuration = ImageAnalysisConfig.Builder().apply {
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
        }.build()
        return ImageAnalysis(configuration).apply {
            setAnalyzer(executor, LuminosityAnalyzer())
        }
    }

}
