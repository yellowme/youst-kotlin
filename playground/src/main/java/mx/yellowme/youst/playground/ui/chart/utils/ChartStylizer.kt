package mx.yellowme.youst.playground.ui.chart.utils

import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import mx.yellowme.youst.playground.domain.ChartSetting

/**
 * Created by adrianleyvasanchez on 03,October,2019
 */
object ChartStylizer {

    fun applyStyle(
        settings: ChartSetting,
        barChart: BarChart,
        activity: AppCompatActivity
    ): BarChart {
        return barChart.apply {
            settings.let {
                setNoDataText(it.noDataText)
                setBorderWidth(it.borderWidth)
                setMaxVisibleValueCount(it.maxVisibleValueCount)
                isLogEnabled = it.logEnabled
                setDrawBarShadow(it.drawBarShadowEnabled)
                setBackgroundColor(getColorByName(it.backgroundColor, activity))
                setDrawGridBackground(it.drawGridBackgroundEnabled)
                setGridBackgroundColor(getColorByName(it.gridBackgroundColor, activity))
                setBorderColor(getColorByName(it.borderColor, activity))
                description = Description()
                    .apply {
                        text = it.description
                        textSize = it.descriptionTextSize
                        textColor = getColorByName(it.descriptionColor, activity)
                    }
            }
        }
    }

    fun applyStyle(
        settings: ChartSetting,
        lineChart: LineChart,
        activity: AppCompatActivity
    ): LineChart {
        return lineChart.apply {
            settings.let {
                setNoDataText(it.noDataText)
                setBorderWidth(it.borderWidth)
                setMaxVisibleValueCount(it.maxVisibleValueCount)
                isLogEnabled = it.logEnabled
                setBackgroundColor(getColorByName(it.backgroundColor, activity))
                setDrawGridBackground(it.drawGridBackgroundEnabled)
                setGridBackgroundColor(getColorByName(it.gridBackgroundColor, activity))
                setBorderColor(getColorByName(it.borderColor, activity))
                description = Description()
                    .apply {
                        text = it.description
                        textSize = it.descriptionTextSize
                        textColor = getColorByName(it.descriptionColor, activity)
                    }
            }
        }
    }

    fun applyStyle(
        settings: ChartSetting,
        bubbleChart: BubbleChart,
        activity: AppCompatActivity
    ): BubbleChart {
        return bubbleChart.apply {
            settings.let {
                setNoDataText(it.noDataText)
                setBorderWidth(it.borderWidth)
                setMaxVisibleValueCount(it.maxVisibleValueCount)
                isLogEnabled = it.logEnabled
                setBackgroundColor(getColorByName(it.backgroundColor, activity))
                setDrawGridBackground(it.drawGridBackgroundEnabled)
                setGridBackgroundColor(getColorByName(it.gridBackgroundColor, activity))
                setBorderColor(getColorByName(it.borderColor, activity))
                description = Description()
                    .apply {
                        text = it.description
                        textSize = it.descriptionTextSize
                        textColor = getColorByName(it.descriptionColor, activity)
                    }
            }
        }
    }

    private fun getColorByName(name: String, activity: AppCompatActivity): Int {
        activity.run {
            resources.let {
                return it.getColor(it.getIdentifier(name, "color", packageName))
            }
        }
    }

}