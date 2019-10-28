package mx.yellowme.youst.playground.ui.chart.utils

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*

/**
 * @author adrianleyvasanchez
 * @since 28,October,2019
 */
object ChartDataInjector {

    fun injectData(
        chart: BarLineChartBase<*>,
        dataSet: BarLineScatterCandleBubbleDataSet<*>
    ) {
        when (chart) {
            is BarChart -> {
                chart.run {
                    data = BarData(dataSet as BarDataSet)
                    invalidate()
                }
            }
            is LineChart -> {
                chart.run {
                    data = LineData(dataSet as LineDataSet)
                    invalidate()
                }
            }
            is BubbleChart -> {
                chart.run {
                    data = BubbleData(dataSet as BubbleDataSet)
                    invalidate()
                }
            }
        }
    }

}