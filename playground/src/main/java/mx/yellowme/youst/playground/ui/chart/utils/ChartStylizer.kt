package mx.yellowme.youst.playground.ui.chart.utils

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
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
        context: Context
    ): BarLineChartBase<*>? {
        return (chart as BarLineChartBase<*>).apply {
            with(settings) {
                setNoDataText(noDataText)
                setBorderWidth(borderWidth)
                setMaxVisibleValueCount(maxVisibleValueCount)
                isLogEnabled = logEnabled
                setBackgroundColor(getColorByName(backgroundColor, context))
                setDrawGridBackground(drawGridBackgroundEnabled)
                setGridBackgroundColor(getColorByName(gridBackgroundColor, context))
                setBorderColor(getColorByName(borderColor, context))
                setDescription(
                    Description().apply {
                        text = description
                        textSize = descriptionTextSize
                        textColor = getColorByName(descriptionColor, context)
                    }
                )
                xAxis.textColor = getColorByName(axisColor, context)
                xAxis.setDrawAxisLine(drawGridBackgroundEnabled)
                xAxis.setDrawLabels(axisLabelsEnabled)
                axisLeft.textColor = getColorByName(axisColor, context)
                axisLeft.setDrawAxisLine(drawGridBackgroundEnabled)
                axisLeft.setDrawLabels(axisLabelsEnabled)
                axisRight.textColor = getColorByName(axisColor, context)
                axisRight.setDrawAxisLine(drawGridBackgroundEnabled)
                axisRight.setDrawLabels(axisLabelsEnabled)
                legend.textColor = getColorByName(legendColor, context)
            }
        }
    }

    fun<T : BarLineScatterCandleBubbleDataSet<*>> applyStyleToDataSet(
        dataSet: T,
        label: String,
        context: Context
    ) = dataSet.run {
        setLabel(label)
        valueTextColor = getColorByName("blue", context)
        valueTypeface = Typeface.DEFAULT_BOLD
        color= getColorByName("yellow", context)
    }

    private fun getColorByName(name: String, context: Context): Int = context.run {
        val colorId = resources.getIdentifier(name, "color", packageName)
        ContextCompat.getColor(this, colorId)
    }

}