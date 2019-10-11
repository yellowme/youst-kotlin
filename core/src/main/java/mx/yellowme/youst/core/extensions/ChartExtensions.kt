package mx.yellowme.youst.core.extensions

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.github.mikephil.charting.charts.BarLineChartBase

/**
 * @author adrianleyvasanchez
 * @since 10,October,2019
 */

fun BarLineChartBase<*>.adjustChartSize() {
    layoutParams.width = MATCH_PARENT
    layoutParams.height = MATCH_PARENT
    invalidate()
}