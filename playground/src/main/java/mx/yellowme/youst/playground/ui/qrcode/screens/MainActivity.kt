package mx.yellowme.youst.playground.ui.qrcode.screens

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_qrcode.*
import mx.yellowme.youst.core.extensions.launchBrowser
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.BarcodeDelegate

/**
 * @author adrianleyvasanchez
 * @since 23,December,2019
 */
class MainActivity : BaseActivity() {

    override val layoutId = R.layout.screen_qrcode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        qrcodeDetector.delegate = object : BarcodeDelegate {
            override fun onPictureScanned(url: String) = launchBrowser(url)
            override fun onError(message: String) = toast(message)
        }

        scanButton.setOnClickListener {
            qrcodeDetector?.scanPicture()
        }
    }

    override fun onStart() {
        super.onStart()
        qrcodeDetector?.start()
    }

    override fun onStop() {
        super.onStop()
        qrcodeDetector?.stop()
    }

}
