package mx.yellowme.youst.playground.ui.chart.utils

import androidx.fragment.app.FragmentActivity
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

    fun buildChart(activity: FragmentActivity): BarLineChartBase<*>? {
        with(activity) {
            parseSettingsJson(this).run {
                instanceTypeChart(this, activity).also {
                    return setConfiguration(this, it, activity)
                }
            }
        }
    }

    fun buildChartByType(activity: FragmentActivity, type: ChartType): BarLineChartBase<*>? {
        with(activity) {
            parseSettingsJson(this).run {
                this?.type = type.toString()
                instanceTypeChart(this, activity).also {
                    return setConfiguration(this, it, activity)
                }
            }
        }
    }

    private fun parseSettingsJson(activity: FragmentActivity): ChartSetting? {
        return activity.loadJsonObject("chart_settings.json")
    }

    private fun instanceTypeChart(settings: ChartSetting?, activity: FragmentActivity): BarLineChartBase<*>?  {
        return when (ChartType.valueOf(settings?.type ?: "")) {
            ChartType.BAR -> BarChart(activity)
            ChartType.LINE -> LineChart(activity)
            ChartType.BUBBLE -> BubbleChart(activity)
        }
    }

    private fun setConfiguration(settings: ChartSetting?, chart: BarLineChartBase<*>?, activity: FragmentActivity): BarLineChartBase<*>? {
        with(chart){
            settings?.let {
                return ChartStylizer.applyStyle(it, this, activity)
            }
        }
        return chart
    }

}