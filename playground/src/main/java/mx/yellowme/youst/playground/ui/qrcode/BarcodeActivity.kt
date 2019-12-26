package mx.yellowme.youst.playground.ui.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.ui.qrcode.screens.QRCodeActivity

/**
 * @author adrianleyvasanchez
 * @since 10,December,2019
 */
class BarcodeActivity : BaseActivity() {

    //region Attributes

    override val layoutId = R.layout.screen_main

    private val myPermissionsCamera = 1

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
    }

    //endregion

    //region Helpers

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            myPermissionsCamera -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    launchActivity()
                }
                return
            }
        }
    }

    private fun requestPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )
        val shouldShowRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermission) {
                toast("Camera permission is necessary to use this feature")
                finish()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    myPermissionsCamera
                )
            }
        } else {
            launchActivity()
        }
    }

    private fun launchActivity() {
        launch<QRCodeActivity>(false)
        finish()
    }

    //endregion

}
