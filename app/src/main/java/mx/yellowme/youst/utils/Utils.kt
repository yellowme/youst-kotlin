package mx.yellowme.youst.utils

import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import java.io.IOException
import java.util.*

fun AppCompatActivity.dipTopx(dip: Int): Int {
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

fun ClassLoader?.readJson(
    named: String,
    withClass: Class<*>
): Any? {

    if (this == null) {
        return null
    }

    val inputStream = getResourceAsStream(named)

    val scanner = Scanner(inputStream!!).useDelimiter("\\A")
    val schemaJSON = if (scanner.hasNext()) scanner.next() else ""

    val adapter = Moshi.Builder().build().adapter(withClass)

    return try {
        adapter.fromJson(schemaJSON)
    } catch (exception: IOException) {
        exception.printStackTrace()
        null
    }

}
