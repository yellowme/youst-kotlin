package mx.yellowme.youst.core.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

/**
 * Read XML attributes for a custom component.
 *
 * Usually on custom widget components we should have and "attrs" variable passed to the
 * class constructor. Given a custom "styleable" you can call this method to retrieve the
 * XML attributes as a [TypedArray]
 */
fun AttributeSet.asTypeArray(context: Context, styleableResId: IntArray): TypedArray? {
    return context.theme.obtainStyledAttributes(
        this, styleableResId, 0, 0
    )
}

/**
 * Allows to access [TypedArray] methods in order to retrieve data by wrapping the
 * [AttributeSet.asTypeArray] method.
 */
inline fun AttributeSet.consumeTypeArray(
    context: Context,
    styleableResId: IntArray,
    consume: TypedArray.() -> Unit = {}
) {

    asTypeArray(context, styleableResId)?.apply {
        consume()
        recycle()
    }
}

inline fun <reified T : Any> String.asInstance(context: Context): T {
    val classRef = Class.forName(this).constructors.first()
    return classRef.newInstance(context) as T
}
