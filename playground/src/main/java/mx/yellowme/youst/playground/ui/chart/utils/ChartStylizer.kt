package mx.yellowme.youst.playground.ui.chart.utils

import android.graphics.Typeface
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet
import mx.yellowme.youst.playground.domain.ChartSetting

/**
 * @author adrianleyvasanchez
 * @since 03,October,2019
 */
@Suppress("SpellCheckingInspection")
object ChartStylizer {

    fun applyStyle(
        settings: ChartSetting,
        chart: BarLineChartBase<*>?,
        activity: FragmentActivity
    ): BarLineChartBase<*>? {
        return (chart as BarLineChartBase<*>).apply {
            with(settings) {
                setNoDataText(noDataText)
                setBorderWidth(borderWidth)
                setMaxVisibleValueCount(maxVisibleValueCount)
                isLogEnabled = logEnabled
                setBackgroundColor(getColorByName(backgroundColor, activity))
                setDrawGridBackground(drawGridBackgroundEnabled)
                setGridBackgroundColor(getColorByName(gridBackgroundColor, activity))
                setBorderColor(getColorByName(borderColor, activity))
                setDescription(
                    Description().apply {
                        text = description
                        textSize = descriptionTextSize
                        textColor = getColorByName(descriptionColor, activity)
                    }
                )
            }
        }
    }

    fun<T : BarLineScatterCandleBubbleDataSet<*>> applyStyleToDataSet(
        dataSet: T,
        label: Int,
        activity: FragmentActivity
    ) = dataSet.run {
        setLabel(activity.getString(label))
        valueTextColor = getColorByName("blue", activity)
        valueTypeface = Typeface.DEFAULT_BOLD
        color= getColorByName("guitar_gold", activity)
    }

    private fun getColorByName(name: String, activity: FragmentActivity): Int = activity.run {
        val colorId = resources.getIdentifier(name, "color", packageName)
        ContextCompat.getColor(this, colorId)
    }

}