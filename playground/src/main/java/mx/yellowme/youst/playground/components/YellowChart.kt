package mx.yellowme.youst.playground.components

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.LineDataSet
import mx.yellowme.youst.core.extensions.consumeTypeArray
import mx.yellowme.youst.core.extensions.inflate
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
class YellowChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    //region Attributes

    var label: String? = ""

    var chart: BarLineChartBase<*>? = null

    var chartSettings: ChartSetting? = null
        set(value) {
            field = value
            updateChartView()
        }

    private var listOfEntries = ArrayList<ChartEntry>()

    //end region

    //region Helpers

    init {
        inflate(R.layout.component_yellowchart, context)
        attrs?.consumeTypeArray(context, R.styleable.AppHero) {
            label = getString(R.styleable.YellowChart_yellowChartLabel)
                ?: context.getString(R.string.showcases_label)
        }
        chartSettings?.let { setup() }
    }

    fun addData(entry: ChartEntry) {
        listOfEntries.add(entry)
        refreshDataSet()
    }

    fun updateType(type: String) {
        chartSettings?.type = type
        updateChartView()
    }

    private fun setup() {
        chart = ChartBuilder.Builder()
            .setActivity(context)
            .setSetting(chartSettings)
            .build()
        updateChartContainerView()
    }

    private fun updateChartView() {
        setup()
        refreshDataSet()
    }

    private fun updateChartContainerView() {
        removeAllViews()
        addView(chart)
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
                        ChartStylizer.applyStyleToDataSet(it, chartSettings!!, context)
                        ChartDataInjector.injectData(chart!!, it)
                    }
            }
            is LineChart -> {
                DataSetConverter.convertDataSetList<LineDataSet>(
                    listOfEntries,
                    ChartType.LINE
                )
                    .let {
                        ChartStylizer.applyStyleToDataSet(it, chartSettings!!, context)
                        ChartDataInjector.injectData(chart!!, it)
                    }
            }
            is BubbleChart -> {
                DataSetConverter.convertDataSetList<BubbleDataSet>(
                    listOfEntries,
                    ChartType.BUBBLE
                )
                    .let {
                        ChartStylizer.applyStyleToDataSet(it, chartSettings!!, context)
                        ChartDataInjector.injectData(chart!!, it)
                    }
            }
        }
    }

    //end region

}

/**
 * The chart size doesn't adjust to parent size automatically, so
 * this method has to be used to match the chart size with the parent size
 * when it's added into the parent view.
 * @return Unit
 */
private fun BarLineChartBase<*>.adjustChartSize() {
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    invalidate()
}