package mx.yellowme.youst.playground.ui.chart.common

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import mx.yellowme.youst.core.hooks.BaseActivity
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

    private var chart: View? = null

    private var chartContainerView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupChart()
    }

    override fun onChangeChart(type: ChartType) {
        //TODO: To implement
        /*
        when (type) {
            ChartType.BAR -> {
                val entries = ArrayList<BarEntry>()
                entries.add(BarEntry(5f, 10f))
                entries.add(BarEntry(10f, 20f))
                entries.add(BarEntry(15f, 30f))
                entries.add(BarEntry(20f, 40f))
                val dataSet = BarDataSet(entries, "Label")
                val barData = BarData(dataSet)
                (chart as BarChart).data = barData
                chart?.invalidate()
            }
            ChartType.LINE -> {
                val entries = ArrayList<Entry>()
                entries.add(Entry(5f, 10f))
                entries.add(Entry(10f, 20f))
                entries.add(Entry(15f, 30f))
                entries.add(Entry(20f, 40f))
                val dataSet = LineDataSet(entries, "Label")
                val lineData = LineData(dataSet)
                (chart as LineChart).data = lineData
                chart?.invalidate()
            }
            ChartType.BUBBLE -> {
                val entries = ArrayList<BubbleEntry>()
                entries.add(BubbleEntry(5f, 10f, 5f))
                entries.add(BubbleEntry(10f, 20f, 5f))
                entries.add(BubbleEntry(15f, 30f, 5f))
                entries.add(BubbleEntry(20f, 40f, 5f))
                val dataSet = BubbleDataSet(entries, "Label")
                val bubbleData = BubbleData(dataSet)
                (chart as BubbleChart).data = bubbleData
                chart?.invalidate()
            }
        }
        */
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

    private fun bindView() {
        chartContainerView = findViewById(chartContainerId)
    }

}