package mx.yellowme.youst.playground.ui.chart.common

import android.os.Bundle
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartType

/**
 * Created by adrianleyvasanchez on 29,September,2019
 */
abstract class BaseChart : BaseActivity(), OnChangeListener {

    override val layoutId: Int = R.layout.screen_chart_view

    abstract val settingsJson: String

    abstract val listener: OnChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupChart()
    }

    override fun onChangeChart(type: ChartType) {
        //TODO: Implement it
    }

    private fun setupChart() {
        //TODO: Implement it
    }

}