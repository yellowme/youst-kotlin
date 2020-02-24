package mx.yellowme.youst.core.domain

import android.Manifest

/**
 * @author adrianleyvasanchez
 * @since 24,February,2020
 */
enum class Permission(val value: String, val requestCode: Int) {
    CAMERA(Manifest.permission.CAMERA, 1),
    VIBRATE(Manifest.permission.VIBRATE, 2),
    INTERNET(Manifest.permission.INTERNET, 3)
}
