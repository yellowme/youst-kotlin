package mx.yellowme.youst.playground.ui.chart.common

import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.ui.chart.utils.ChartBuilder
import mx.yellowme.youst.playground.ui.chart.utils.ChartDataInjector.injectData
import mx.yellowme.youst.playground.ui.chart.utils.ChartStylizer.applyStyleToDataSet
import mx.yellowme.youst.playground.ui.chart.utils.DataSetConverter.convertDataSetList

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
abstract class BaseChartFragment : BaseFragment() {

    /**
     * The @chartContainerId Int instance represents the Layout id
     * where the chart is going to be drawn.
     */
    abstract val chartContainerId: Int

    /**
     * The @settingsJsonPath String instance represent the path where
     * is going to be located the chart settings file that will allow
     * set all chart configurations, including style, structure
     * and constraints. It's necessary define this variable in
     * the child class.
     */
    abstract val settingsJsonPath: String

    protected var chart: BarLineChartBase<*>? = null

    protected val chartLabel: Int = R.string.showcases_label

    private var chartContainerView: ViewGroup? = null

    private var listOfEntries: ArrayList<ChartEntry>? = ArrayList()

    override fun onViewReady() {
        bindView()
        setupChart()
    }

    fun updateChartType(type: ChartType) {
        chart = ChartBuilder.Builder()
            .setActivity(activity!!)
            .setSettingsJsonPath(settingsJsonPath)
            .setType(type)
            .build()
        updateChartContainerView()
        updateDataSet()
    }

    fun updateDataSet(entry: ChartEntry) {
        listOfEntries?.add(entry)
        updateDataSet()
    }

    private fun bindView() {
        chartContainerView = activity?.findViewById(chartContainerId)
    }

    private fun setupChart() {
        chart = ChartBuilder.Builder()
            .setActivity(activity!!)
            .setSettingsJsonPath(settingsJsonPath)
            .build()
        updateChartContainerView()
    }

    private fun updateChartContainerView() {
        chartContainerView?.run {
            removeAllViews()
            addView(chart)
        }
        chart?.adjustChartSize()
    }

    private fun updateDataSet() {
        when (chart) {
            is BarChart -> {
                convertDataSetList<BarDataSet>(listOfEntries ?: ArrayList(), ChartType.BAR)
                    .let {
                        applyStyleToDataSet(it, chartLabel, activity!!)
                        injectData(chart!!, it)
                    }
            }
            is LineChart -> {
                convertDataSetList<LineDataSet>(listOfEntries ?: ArrayList(), ChartType.LINE)
                    .let {
                        applyStyleToDataSet(it, chartLabel, activity!!)
                        injectData(chart!!, it)
                    }
            }
            is BubbleChart -> {
                convertDataSetList<BubbleDataSet>(listOfEntries ?: ArrayList(), ChartType.BUBBLE)
                    .let {
                        applyStyleToDataSet(it, chartLabel, activity!!)
                        injectData(chart!!, it)
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
