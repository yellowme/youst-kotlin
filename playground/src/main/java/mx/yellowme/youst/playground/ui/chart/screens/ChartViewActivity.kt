package mx.yellowme.youst.playground.ui.chart.screens

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.screen_chart_view.*
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.data.ChartFakeRepository
import mx.yellowme.youst.playground.data.SingleItemCallback
import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.ui.chart.common.BaseChartActivity

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
        chartSelector?.setup(this, listener!!)
        repeat(20) {
            getData()
        }
    }

    //TODO: Must delegate action to another layer component (ViewModel or Presenter)
    private fun getData() {
        ChartFakeRepository().getData(true,
            object : SingleItemCallback<ChartEntry> {
                override fun onServerError(message: String) {
                    Log.d("tag", message)
                }

                override fun onLoad(item: ChartEntry?) {
                    item?.let {
                        listener?.onChangeDataSet(item)
                    }
                }
        })
    }

}