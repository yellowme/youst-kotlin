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

    private var chart: BarLineChartBase<*>? = null

    private var chartData: BarLineScatterCandleBubbleData<*>? = null

    private var chartDataSet: Any? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupChart()
    }

    override fun onChangeChart(type: ChartType) {
        //TODO: To implement
    }

    override fun onChangeDataSet(entry: ChartEntry) {
        val chartEntries = ArrayList<Entry>()
        val label = getString(R.string.showcases_label)

        listOfEntries?.let {
            it.add(entry)
            for (item in it) {
                when (chart) {
                    is BarChart -> {
                        chartEntries.add(BarEntry(item.x, item.y))
                        BarDataSet(chartEntries.toList() as List<BarEntry>, label).run {
                            chartDataSet = this
                            chartData = BarData(this)
                        }
                    }
                    is LineChart -> {
                        chartEntries.add(Entry(item.x, item.y))
                        LineDataSet(chartEntries.toList(), label).run {
                            chartDataSet = this
                            chartData = LineData(this)
                        }
                    }
                    is BubbleChart -> {
                        chartEntries.add(BubbleEntry(item.x, item.y, item.z ?: 0f))
                        BubbleDataSet(chartEntries.toList() as List<BubbleEntry>, label).run {
                            chartDataSet = this
                            chartData = BubbleData(this)
                        }
                    }
                }
                chart?.run {
                    data = chartData
                    invalidate()
                }
            }
        }
    }

    private fun bindView() {
        chartContainerView = findViewById(chartContainerId)
    }

    private fun setupChart() {
        chart = ChartBuilder.buildChart(this)
        chartContainerView?.addView(chart)
        chart?.let {
            it.layoutParams.width = MATCH_PARENT
            it.layoutParams.height = MATCH_PARENT
            it.invalidate()
        }
    }

}
