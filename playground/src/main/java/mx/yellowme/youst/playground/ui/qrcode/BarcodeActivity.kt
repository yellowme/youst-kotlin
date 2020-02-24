package mx.yellowme.youst.playground.ui.qrcode

import android.content.pm.PackageManager
import android.os.Bundle
import mx.yellowme.youst.core.domain.Permission
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.utils.PermissionsDelegate
import mx.yellowme.youst.core.utils.YoustPermissions
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.ui.qrcode.screens.QRCodeActivity

/**
 * @author adrianleyvasanchez
 * @since 10,December,2019
 */
class BarcodeActivity : BaseActivity() {

    //region Attributes

    override val layoutId = R.layout.screen_main

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        YoustPermissions.Builder()
            .setActivity(this)
            .setPermissions(Permission.CAMERA)
            .setRationaleMessage("Camera permission is necessary to use this feature")
            .setDelegate(object : PermissionsDelegate {
                override fun onPermissionsGranted() = launchActivity()
                override fun onPermissionsDenied() = finish()
            })
            .askForPermissions()
    }

    //endregion

    //region Helpers

    private fun launchActivity() {
        launch<QRCodeActivity>(false)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) = when (requestCode) {
        Permission.CAMERA.requestCode -> {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                launchActivity()
            } else {
                finish()
            }
        }
        else -> {
        }
    }

    //endregion

}
