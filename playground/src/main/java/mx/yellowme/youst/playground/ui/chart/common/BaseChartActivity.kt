package mx.yellowme.youst.playground.ui.chart.common

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.ui.chart.utils.ChartBuilder
import mx.yellowme.youst.playground.ui.chart.utils.DataSetConverter.convertDataSetList

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
abstract class BaseChartActivity : BaseActivity(), OnChangeListener {

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

    var listener: OnChangeListener? = null

    private var chartContainerView: LinearLayout? = null

    private var listOfEntries: ArrayList<ChartEntry>? = ArrayList()

    protected var chart: BarLineChartBase<*>? = null

    protected val chartLabel: Int = R.string.showcases_label

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupChart()
    }

    override fun onChangeChart(type: ChartType) {
        chart = ChartBuilder.buildChartByType(this, type)
        chartContainerView?.run {
            removeAllViews()
            addView(chart)
        }
        adjustChartSize()
        updateDataSet()
    }

    override fun onChangeDataSet(entry: ChartEntry) {
        listOfEntries?.add(entry)
        updateDataSet()
    }

    private fun bindView() {
        chartContainerView = findViewById(chartContainerId)
    }

    private fun setupChart() {
        chart = ChartBuilder.buildChart(this)
        chartContainerView?.addView(chart)
        adjustChartSize()
    }

    private fun adjustChartSize() {
        chart?.let {
            it.layoutParams.width = MATCH_PARENT
            it.layoutParams.height = MATCH_PARENT
            it.invalidate()
        }
    }

    private fun updateDataSet() {
        when (chart) {
            is BarChart -> {
                convertDataSetList<BarDataSet>(listOfEntries ?: ArrayList(), ChartType.BAR)
                    .let {
                        it.label = getString(chartLabel)
                        (chart as BarChart).run {
                            data = BarData(it)
                            invalidate()
                        }
                    }
            }
            is LineChart -> {
                convertDataSetList<LineDataSet>(listOfEntries ?: ArrayList(), ChartType.LINE)
                    .let {
                        it.label = getString(chartLabel)
                        (chart as LineChart).run {
                            data = LineData(it)
                            invalidate()
                        }
                    }
            }
            is BubbleChart -> {
                convertDataSetList<BubbleDataSet>(listOfEntries ?: ArrayList(), ChartType.BUBBLE)
                    .let {
                        it.label = getString(chartLabel)
                        (chart as BubbleChart).run {
                            data = BubbleData(it)
                            invalidate()
                        }
                    }
            }
        }
    }

}
