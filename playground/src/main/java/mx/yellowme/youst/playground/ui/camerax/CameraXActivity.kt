package mx.yellowme.youst.playground.ui.camerax

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.screen_camerax.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R

/**
 * @author adrianleyvasanchez
 * @since 10,December,2019
 */
class CameraXActivity : BaseActivity(), LifecycleOwner {

    //region Attributes

    override val layoutId = R.layout.screen_camerax

    private val requestCodePermission = 10

    private val requiredPermissions = arrayOf(Manifest.permission.CAMERA)

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (allPermissionsGranted()) {
            initializeCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, requiredPermissions, requestCodePermission)
        }
    }

    //endregion

    //region Helpers

    private fun initializeCamera() {
        cameraxy.run {
            lifecycleOwner = this@CameraXActivity
            startCamera()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == requestCodePermission) {
            if (allPermissionsGranted()) {
                initializeCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = requiredPermissions.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    //endregion
}
