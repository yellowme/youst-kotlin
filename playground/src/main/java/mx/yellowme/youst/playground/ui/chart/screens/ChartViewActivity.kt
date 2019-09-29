package mx.yellowme.youst.playground.ui.chart.screens

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_chart_view.*
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.domain.getTypeByName
import mx.yellowme.youst.playground.ui.chart.common.BaseChart
import mx.yellowme.youst.playground.ui.chart.common.OnChangeListener

/**
 * Created by adrianleyvasanchez on 29,September,2019
 */
class ChartViewActivity : BaseChart() {

    //Region attributes

    override val settingsJson: String
        get() = "./chart_settings.json"

    override val listener: OnChangeListener
        get() = this

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDefaultOptions()
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            //TODO: Change strings by enums
            when(checkedId) {
                optionOne.id -> {
                    listener.onChangeChart(
                        getTypeByName(optionOne.text.toString())
                    )
                }
                optionTwo.id -> {
                    listener.onChangeChart(
                        getTypeByName(optionTwo.text.toString())
                    )

                }
                optionThree.id -> {
                    listener.onChangeChart(
                        getTypeByName(optionThree.text.toString())
                    )
                }
            }
        }
    }

    private fun initializeDefaultOptions() {
        optionOne.text = ChartType.BAR.name
        optionTwo.text = ChartType.LINE.name
        optionThree.text = ChartType.BUBBLE.name
    }
}