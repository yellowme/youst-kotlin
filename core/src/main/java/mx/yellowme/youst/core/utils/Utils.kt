package mx.yellowme.youst.core.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import mx.yellowme.youst.core.extensions.launch
import java.io.IOException
import java.util.Scanner

//region AppCompatActivity

fun AppCompatActivity.dipToPx(dip: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip.toFloat(),
        resources.displayMetrics
    ).toInt()
}

fun <T : View> AppCompatActivity.findOrThrow(viewId: Int): T {
    return findViewById(viewId) ?: throw RuntimeException(
        "Screen must contain a reference to component with id: $viewId"
    )
}

//endregion

//region Activity Helpers

fun Activity.readJson(named: String): String? {
    println("Reading JSON file named $named")
    return classLoader?.readJson(named)
}

inline fun <reified T : Any> Activity.loadJsonArray(named: String): List<T>? {
    return readJson(named)?.asJsonArrayOf()
}

inline fun <reified T : Any> Activity.loadJsonObject(named: String): T? {
    return readJson(named)?.asJsonObject()
}

fun Activity.launchExternalEmailChooser(address: String, subject: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        val addressArray = ArrayList<String>(1)
        addressArray.add(address)
        putExtra(Intent.EXTRA_EMAIL, addressArray.toTypedArray())
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    launch(intent)
}

//endregion

//region Generic Readers

fun ClassLoader?.readJson(named: String): String? {
    if (this == null) {
        return null
    }

    val inputStream = getResourceAsStream(named)
    val scanner = Scanner(inputStream!!).useDelimiter("\\A")
    return if (scanner.hasNext()) scanner.next() else ""
}

inline fun <reified T : Any> String.asJsonObject(): T? {
    var modelFromJson: T? = null
    try {
        val adapter = Moshi.Builder().build().adapter(T::class.java)
        modelFromJson = adapter.fromJson(this) as T
    } catch (exception: IOException) {
        exception.printStackTrace()
    } finally {
        return modelFromJson
    }
}

inline fun <reified T : Any> String.asJsonArrayOf(): List<T>? {
    var modelFromJson: List<T>? = null
    try {
        val type = newParameterizedType(List::class.java, T::class.java)
        val adapter: JsonAdapter<List<T>> = Moshi.Builder().build().adapter(type)
        modelFromJson = adapter.fromJson(this) as List<T>
    } catch (exception: IOException) {
        exception.printStackTrace()
    } finally {
        return modelFromJson
    }
}

//endregion
