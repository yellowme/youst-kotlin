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
class ChartBuilder private constructor(
    private val activity: FragmentActivity?,
    private val settingsJsonPath: String?,
    private val type: ChartType? = null) {

    data class Builder(
        private var activity: FragmentActivity? = null,
        private var settingsJsonPath: String? = null,
        private var type: ChartType? = null) {

        fun setActivity(activity: FragmentActivity) = apply { this.activity = activity }

        fun setSettingsJsonPath(settingsJsonPath: String) = apply { this.settingsJsonPath = settingsJsonPath }

        fun setType(type: ChartType?) = apply { this.type = type }

        fun build(): BarLineChartBase<*>? = ChartBuilder(activity, settingsJsonPath, type).run {
                type?.let {
                    buildChartByType()
                } ?: buildChart()
        }

    }

    private fun buildChart(): BarLineChartBase<*>? {
        return parseSettingsJson().run {
            setConfiguration(this, instanceTypeChart(this))
        }
    }

    private fun buildChartByType(): BarLineChartBase<*>? {
        return parseSettingsJson().run {
            this?.type = type.toString()
            setConfiguration(this, instanceTypeChart(this))
        }
    }

    private fun parseSettingsJson(): ChartSetting? = activity?.loadJsonObject(settingsJsonPath!!)

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