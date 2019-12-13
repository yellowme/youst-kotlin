package mx.yellowme.youst.playground.components

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.camera.core.CameraX
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.component_cameraxy.view.*
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.ui.camerax.utils.CameraFactory
import java.io.File
import java.util.concurrent.Executors


/**
 * @author adrianleyvasanchez
 * @since 11,December,2019
 */
class CameraXY @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    //region Attributes

    var lifecycleOwner: LifecycleOwner? = null
        set(value) {
            field = value
            startCamera()
        }

    private val executor = Executors.newSingleThreadExecutor()

    private var preview: Preview? = null

    private var imageCapture: ImageCapture? = null

    private var imageAnalysis: ImageAnalysis? = null

    //endregion

    //region Helpers

    init {
        inflate(R.layout.component_cameraxy, context)
        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            viewFinder.updateTransform()
        }
    }

    fun startCamera() {
        viewFinder.post {
            preview = CameraFactory.createPreviewWithConfig()
            imageCapture = CameraFactory.createImageCaptureWithConfig()
            imageAnalysis = CameraFactory.createImageAnalysisWithConfig(executor)

            preview?.setOnPreviewOutputUpdateListener {
                val parent = viewFinder.parent as ViewGroup
                parent.removeView(viewFinder)
                parent.addView(viewFinder, 0)
                viewFinder.run {
                    surfaceTexture = it.surfaceTexture
                    updateTransform()
                }
            }

            CameraX.bindToLifecycle(lifecycleOwner, preview, imageCapture, imageAnalysis)
        }
    }

    fun captureImage() {
        viewFinder.post {
            val file = File(context.externalMediaDirs.first(),
                "${System.currentTimeMillis()}.jpg")
            imageCapture?.takePicture(file, executor,
                object : ImageCapture.OnImageSavedListener {
                    override fun onError(
                        imageCaptureError: ImageCapture.ImageCaptureError,
                        message: String,
                        exc: Throwable?
                    ) {
                        viewFinder.post {
                            Toast.makeText(context, "Photo capture failed: $message", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onImageSaved(file: File) {
                        viewFinder.post {
                            Toast.makeText(context, "Photo capture succeeded: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }

    //endregion
}

fun TextureView.updateTransform() {
    val matrix = Matrix()
    val centerX = width / 2f
    val centerY = height / 2f

    val rotationDegrees = when(display.rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        Surface.ROTATION_270 -> 270
        else -> return
    }
    matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

    setTransform(matrix)
}
