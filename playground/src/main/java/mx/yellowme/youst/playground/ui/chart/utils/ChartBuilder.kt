package mx.yellowme.youst.playground.ui.chart.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import mx.yellowme.youst.core.utils.loadJsonObject
import mx.yellowme.youst.playground.domain.ChartSetting
import mx.yellowme.youst.playground.domain.ChartType

/**
 * @author adrianleyvasanchez
 * @since 03,October,2019
 */
object ChartBuilder {

    fun buildChart(activity: AppCompatActivity): BarLineChartBase<*>? {
        with(activity) {
            parseSettingsJson(this).run {
                instanceTypeChart(this, activity).also {
                    return setConfiguration(this, it, activity)
                }
            }
        }
    }

    private fun parseSettingsJson(activity: AppCompatActivity): ChartSetting? {
        return activity.loadJsonObject("chart_settings.json")
    }

    private fun instanceTypeChart(settings: ChartSetting?, activity: AppCompatActivity): BarLineChartBase<*>?  {
        return when (ChartType.valueOf(settings?.type ?: "")) {
            ChartType.BAR -> BarChart(activity)
            ChartType.LINE -> LineChart(activity)
            ChartType.BUBBLE -> BubbleChart(activity)
        }
    }

    private fun setConfiguration(settings: ChartSetting?, chart: BarLineChartBase<*>?, activity: AppCompatActivity): BarLineChartBase<*>? {
        with(chart){
            settings?.let {
                return ChartStylizer.applyStyle(it, this, activity)
            }
        }
        return chart
    }

}