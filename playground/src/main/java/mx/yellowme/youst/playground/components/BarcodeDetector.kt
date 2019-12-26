package mx.yellowme.youst.playground.components

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import io.fotoapparat.Fotoapparat
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import kotlinx.android.synthetic.main.component_barcode_detector.view.*
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.playground.R

interface BarcodeDelegate {

    fun onPictureScanned(url: String)

    fun onError(message: String)

}

/**
 * @author adrianleyvasanchez
 * @since 18,December,2019
 */
class BarcodeDetector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    //region Attributes

    var delegate: BarcodeDelegate? = null

    var barcodeFormat: Int = Barcode.QR_CODE

    private lateinit var detector: BarcodeDetector

    private var camera: Fotoapparat? = null

    //endregion

    //region Public methods

    init {
        inflate(R.layout.component_barcode_detector, context)
        setup()
    }

    fun start() = camera?.start()

    fun stop() = camera?.stop()

    fun scanPicture() = camera?.run {
        takePicture().toBitmap().whenAvailable {
            scan(it?.bitmap)
        }
    }

    //endregion

    //region Private methods

    private fun setup() {
        detector = BarcodeDetector.Builder(context)
            .setBarcodeFormats(barcodeFormat)
            .build()

        if (detector.isOperational.not()) {
            delegate?.onError("Couldn't setup the detector")
        }

        initializeCamera()
    }

    private fun initializeCamera() {
        camera = Fotoapparat(
            context = context,
            view = cameraView,
            scaleType = ScaleType.CenterCrop,
            lensPosition = back(),
            cameraErrorCallback = { error ->
                delegate?.onError(error.localizedMessage!!)
            }
        )
    }

    private fun scan(bitmap: Bitmap?) {
        val frame = Frame.Builder().setBitmap(bitmap).build()
        detector.detect(frame)?.let {
            if (it.size() > 0) {
                val firstResult = 0
                val value = it.valueAt(firstResult).rawValue
                delegate?.onPictureScanned(value)
            } else {
                delegate?.onError("QR code not detected")
            }
        } ?: delegate?.onError("There's a problem detecting QR codes")
    }

    //endregion

}
