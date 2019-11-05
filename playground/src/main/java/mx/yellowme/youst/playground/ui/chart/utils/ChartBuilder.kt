package mx.yellowme.youst.playground.ui.chart.utils

import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import mx.yellowme.youst.playground.domain.ChartSetting
import mx.yellowme.youst.playground.domain.ChartType

/**
 * @author adrianleyvasanchez
 * @since 03,October,2019
 */
class ChartBuilder private constructor(
    private val activity: FragmentActivity?,
    private val settings: ChartSetting?
) {

    data class Builder(
        private var activity: FragmentActivity? = null,
        private var settings: ChartSetting? = null
    ) {

        fun setActivity(activity: FragmentActivity) = apply { this.activity = activity }

        fun setSetting(settings: ChartSetting?) = apply { this.settings = settings }

        fun build(): BarLineChartBase<*>? = ChartBuilder(activity, settings).buildChart()

    }

    private fun buildChart(): BarLineChartBase<*>? {
        return setConfiguration(settings, instanceTypeChart(settings))
    }

    private fun instanceTypeChart(settings: ChartSetting?): BarLineChartBase<*>?  {
        return when (ChartType.valueOf(settings?.type ?: "")) {
            ChartType.BAR -> BarChart(activity)
            ChartType.LINE -> LineChart(activity)
            ChartType.BUBBLE -> BubbleChart(activity)
        }
    }

    private fun setConfiguration(settings: ChartSetting?, chart: BarLineChartBase<*>?): BarLineChartBase<*>? =
        settings?.let {
            ChartStylizer.applyStyle(it, chart, activity!!)
        } ?: chart

}