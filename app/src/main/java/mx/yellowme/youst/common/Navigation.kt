package mx.yellowme.youst.common;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.start(
    destinationClass: Class<*>,
    extrasMap: HashMap<String, String?>? = null
) {
    val intent = Intent().setClass(this, destinationClass)

    if (extrasMap != null && extrasMap.isNotEmpty()) {
        for ((key, value) in extrasMap) {
            intent.putExtra(key, value)
        }
    }

    startActivity(intent)
}
