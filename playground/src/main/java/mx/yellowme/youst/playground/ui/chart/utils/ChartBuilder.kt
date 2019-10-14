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
        return parseSettingsJson(activity).run {
            setConfiguration(this, instanceTypeChart(this, activity), activity)
        }
    }

    fun buildChartByType(activity: FragmentActivity, type: ChartType): BarLineChartBase<*>? {
        return parseSettingsJson(activity).run {
            this?.type = type.toString()
            setConfiguration(this, instanceTypeChart(this, activity), activity)
        }
    }

    private fun parseSettingsJson(activity: FragmentActivity): ChartSetting? =
        activity.loadJsonObject("chart_settings.json")


    private fun instanceTypeChart(settings: ChartSetting?, activity: FragmentActivity): BarLineChartBase<*>?  {
        return when (ChartType.valueOf(settings?.type ?: "")) {
            ChartType.BAR -> BarChart(activity)
            ChartType.LINE -> LineChart(activity)
            ChartType.BUBBLE -> BubbleChart(activity)
        }
    }

    private fun setConfiguration(settings: ChartSetting?, chart: BarLineChartBase<*>?, activity: FragmentActivity): BarLineChartBase<*>? =
        settings?.let {
            return ChartStylizer.applyStyle(it, chart, activity)
        } ?: chart

}