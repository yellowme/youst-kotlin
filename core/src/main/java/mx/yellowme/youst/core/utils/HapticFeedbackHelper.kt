package mx.yellowme.youst.core.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object HapticFeedbackHelper {

    private const val DEFAULT_MILLS = 3L
    private const val DEVICES_BELOW_API_26_MILLS = 15L

    fun generateTouchFeedbackOn(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (vibrator.hasVibrator().not()) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createOneShot(
                DEFAULT_MILLS,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
            vibrator.vibrate(effect)
            return
        }

        @Suppress("DEPRECATION")
        vibrator.vibrate(DEVICES_BELOW_API_26_MILLS)
    }

}
