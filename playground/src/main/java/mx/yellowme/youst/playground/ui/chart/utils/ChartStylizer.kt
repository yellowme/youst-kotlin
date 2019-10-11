package mx.yellowme.youst.playground.ui.chart.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.Description
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
        activity: AppCompatActivity
    ): BarLineChartBase<*>? {
        return (chart as BarLineChartBase<*>)
            .apply {
                settings.let {
                    setNoDataText(it.noDataText)
                    setBorderWidth(it.borderWidth)
                    setMaxVisibleValueCount(it.maxVisibleValueCount)
                    isLogEnabled = it.logEnabled
                    setBackgroundColor(getColorByName(it.backgroundColor, activity))
                    setDrawGridBackground(it.drawGridBackgroundEnabled)
                    setGridBackgroundColor(getColorByName(it.gridBackgroundColor, activity))
                    setBorderColor(getColorByName(it.borderColor, activity))
                    setDescription(
                        Description()
                            .apply {
                                text = it.description
                                textSize = it.descriptionTextSize
                                textColor = getColorByName(it.descriptionColor, activity)
                            }
                    )
                }
            }
    }

    private fun getColorByName(name: String, activity: AppCompatActivity): Int {
        activity.run {
            resources.let {
                val colorId = it.getIdentifier(name, "color", packageName)
                return ContextCompat.getColor(this, colorId)
            }
        }
    }

}