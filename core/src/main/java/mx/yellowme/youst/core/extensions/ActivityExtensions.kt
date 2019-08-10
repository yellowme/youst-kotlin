package mx.yellowme.youst.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

//region Toast

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

//endregion

//region Launch Activities

inline fun <reified T : Any> Activity.launchAndFinish(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
    //TODO: Enable fade transitions
    //overridePendingTransition(R.anim.enter, R.anim.exit)
    finish()
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

//endregion
