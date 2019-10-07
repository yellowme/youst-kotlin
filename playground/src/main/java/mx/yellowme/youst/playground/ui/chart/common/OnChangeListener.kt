package mx.yellowme.youst.playground.ui.chart.common

import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.domain.ChartType

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
interface OnChangeListener {

    fun onChangeChart(type: ChartType)

    fun onChangeDataSet(entry: ChartEntry)

}