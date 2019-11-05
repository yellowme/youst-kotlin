package mx.yellowme.youst.playground.components

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.LineDataSet
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.domain.ChartSetting
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.ui.chart.utils.ChartBuilder
import mx.yellowme.youst.playground.ui.chart.utils.ChartDataInjector
import mx.yellowme.youst.playground.ui.chart.utils.ChartStylizer
import mx.yellowme.youst.playground.ui.chart.utils.DataSetConverter

/**
 * @author adrianleyvasanchez
 * @since 04,November,2019
 */

class YellowChart(private val activity: FragmentActivity) {
    /**
     * The @chartContainerId Int instance represents the Layout id
     * where the chart is going to be drawn.
     */
    var chartContainerId: Int? = 0
    
    var chart: BarLineChartBase<*>? = null

    var label: Int = R.string.showcases_label

    var chartSettings: ChartSetting? = null
        set(value) {
            field = value
            updateChartView()
        }

    private var listOfEntries: ArrayList<ChartEntry> = ArrayList()

    fun setupChart() {
        chart = ChartBuilder.Builder()
            .setActivity(activity)
            .setSetting(chartSettings)
            .build()
        updateChartContainerView()
    }

    fun addData(entry: ChartEntry) {
        listOfEntries.add(entry)
        refreshDataSet()
    }

    fun updateType(type: String) {
        chartSettings?.type = type
        updateChartView()
    }

    private fun updateChartView() {
        chart = ChartBuilder.Builder()
            .setActivity(activity)
            .setSetting(chartSettings)
            .build()
        updateChartContainerView()
        refreshDataSet()
    }

    private fun updateChartContainerView() {
        activity.findViewById<ViewGroup>(chartContainerId!!).run {
            removeAllViews()
            addView(chart)
        }
        chart?.adjustChartSize()
    }

    private fun refreshDataSet() {
        when (chart) {
            is BarChart -> {
                DataSetConverter.convertDataSetList<BarDataSet>(
                    listOfEntries,
                    ChartType.BAR
                )
                    .let {
                        ChartStylizer.applyStyleToDataSet(it, label, activity)
                        ChartDataInjector.injectData(chart!!, it)
                    }
            }
            is LineChart -> {
                DataSetConverter.convertDataSetList<LineDataSet>(
                    listOfEntries,
                    ChartType.LINE
                )
                    .let {
                        ChartStylizer.applyStyleToDataSet(it, label, activity)
                        ChartDataInjector.injectData(chart!!, it)
                    }
            }
            is BubbleChart -> {
                DataSetConverter.convertDataSetList<BubbleDataSet>(
                    listOfEntries,
                    ChartType.BUBBLE
                )
                    .let {
                        ChartStylizer.applyStyleToDataSet(it, label, activity)
                        ChartDataInjector.injectData(chart!!, it)
                    }
            }
        }
    }

}

/**
 * The chart size doesn't adjust to parent size automatically, so
 * this method has to be used to match the chart size with the parent size
 * when it's added into the parent view.
 * @return Unit
 */
fun BarLineChartBase<*>.adjustChartSize() {
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    invalidate()
}