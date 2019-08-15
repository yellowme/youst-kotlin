package mx.yellowme.youst.core.extensions

import android.content.Context
import android.widget.Toast

//region Toast

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

//endregion