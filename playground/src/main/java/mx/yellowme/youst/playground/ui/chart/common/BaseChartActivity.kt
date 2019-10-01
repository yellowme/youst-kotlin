package mx.yellowme.youst.playground.ui.chart.common

import android.os.Bundle
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartType

/**
 * Created by adrianleyvasanchez on 29,September,2019
 */
abstract class BaseChartActivity : BaseActivity(), OnChangeListener {

    /**
     * The @chartContainerId Int instance represents the Layout id
     * where the chart is going to be drawn.
     */
    abstract val chartContainerId: Int

    /**
     * The @settingsJson String instance represent the path where
     * is going to be located the chart settings file.
     */
    abstract val settingsJson: String


    abstract val listener: OnChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupChart()
    }

    override fun onChangeChart(type: ChartType) {
        //TODO: To implement
    }

    private fun setupChart() {
        //TODO: To implement
    }

}