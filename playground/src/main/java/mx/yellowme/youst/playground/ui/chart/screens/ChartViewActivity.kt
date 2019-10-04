package mx.yellowme.youst.playground.ui.chart.screens

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_chart_view.*
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.ui.chart.common.BaseChartActivity
import mx.yellowme.youst.playground.components.ChartSelectorActionListener

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
class ChartViewActivity : BaseChartActivity() {

    //Region attributes

    override val layoutId = R.layout.screen_chart_view

    override val chartContainerId = R.id.chartContainer

    override val settingsJsonPath = "./chart_settings.json"

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = this
        chartSelector.run {
            firstOptionText = ChartType.BAR.name
            secondOptionText = ChartType.LINE.name
            thirdOptionText = ChartType.BUBBLE.name
            actionListener = object : ChartSelectorActionListener {
                override fun onChangeAction(checkedType: ChartType) {
                    listener?.onChangeChart(checkedType)
                }
            }
        }
    }

}