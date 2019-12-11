package mx.yellowme.youst.playground.components

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.component_cameraxy.view.*
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.playground.R
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

    private val executor = Executors.newSingleThreadExecutor()

    //endregion

    //region Helpers

    init {
        inflate(R.layout.component_cameraxy, context)
        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
    }

    fun startCamera() {
        viewFinder.post {
            val previewConfig = PreviewConfig.Builder().apply {
                setTargetResolution(Size(640, 480))
            }.build()

            val preview = Preview(previewConfig)
            preview.setOnPreviewOutputUpdateListener {
                val parent = viewFinder.parent as ViewGroup
                parent.removeView(viewFinder)
                parent.addView(viewFinder, 0)
                viewFinder.surfaceTexture = it.surfaceTexture
                updateTransform()
            }

            CameraX.bindToLifecycle(lifecycleOwner, preview)
        }
    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        val rotationDegrees = when(viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        viewFinder.setTransform(matrix)
    }

    //endregion

}
