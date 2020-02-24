package mx.yellowme.youst.core.utils

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import mx.yellowme.youst.core.domain.Permission
import mx.yellowme.youst.core.extensions.toast

/**
 * @author adrianleyvasanchez
 * @since 10,January,2020
 */
interface PermissionsDelegate {

    fun onPermissionsGranted()

    fun onPermissionsDenied()

}

class YoustPermissions private constructor(
    private var activity: AppCompatActivity,
    private var permissions: List<Permission>,
    private var rationaleMessage: String? = null,
    private var delegate: PermissionsDelegate? = null
) {

    data class Builder(
        private var activity: AppCompatActivity? = null,
        private var permissions: List<Permission>? = null,
        private var rationaleMessage: String? = null,
        private var delegate: PermissionsDelegate? = null
    ) {

        fun setActivity(activity: AppCompatActivity) = apply { this.activity = activity }

        fun setPermissions(vararg permissions: Permission) =
            apply { this.permissions = permissions.toList() }

        fun setRationaleMessage(rationaleMessage: String) =
            apply { this.rationaleMessage = rationaleMessage }

        fun setDelegate(delegate: PermissionsDelegate) = apply { this.delegate = delegate }

        fun askForPermissions() = activity?.let {
            YoustPermissions(
                it,
                permissions ?: ArrayList(),
                rationaleMessage,
                delegate
            ).askForPermissions()
        }

    }

    private fun askForPermissions() {
        for (singlePermission in permissions) {
            val checkSelfPermission = ContextCompat.checkSelfPermission(
                activity, singlePermission.value
            )
            val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                activity, singlePermission.value
            )

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRationale) {
                    activity.toast(rationaleMessage ?: "Permissions are necessary")
                    delegate?.onPermissionsDenied()
                } else {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(singlePermission.value),
                        Permission.valueOf(singlePermission.name).requestCode
                    )
                }
            } else {
                delegate?.onPermissionsGranted()
            }
        }
    }
}
