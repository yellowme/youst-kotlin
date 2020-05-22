package mx.yellowme.youst.core.extensions

import android.app.Activity
import android.os.Bundle

fun <T> Activity.extraOrDefault(
    forKey: String,
    default: T?,
    abortByThrowing: Boolean = false,
    extraction: Bundle.() -> T
): T {
    val message = "Must provide params.\nRequired key: $forKey"

    intent.extras?.let {
        return when {
            it.containsKey(forKey) -> it.extraction()
            default != null -> default
            else -> throw RuntimeException(message)
        }
    } ?: if (abortByThrowing) throw RuntimeException(message) else return default!!
}

fun Activity.extraStringOrThrow(forKey: String): String? {
    return extraOrDefault(forKey, null, true) {
        getString(forKey)
    }
}

fun Activity.extraBooleanOrThrow(forKey: String): Boolean {
    return extraOrDefault(forKey, null, true) {
        getBoolean(forKey, false)
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Activity.extraSerializableOrThrow(forKey: String): T {
    return extraOrDefault(forKey, null, true) {
        getSerializable(forKey) as T
    }
}
