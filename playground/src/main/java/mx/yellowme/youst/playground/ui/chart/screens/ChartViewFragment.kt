package mx.yellowme.youst.playground.ui.chart.screens

import android.util.Log
import kotlinx.android.synthetic.main.screen_chart_view.*
import mx.yellowme.youst.core.utils.loadJsonObject
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.OnChangeListener
import mx.yellowme.youst.playground.data.ChartFakeRepository
import mx.yellowme.youst.playground.data.SingleItemCallback
import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.domain.ChartSetting
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.ui.chart.common.BaseChartFragment

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
class ChartViewFragment : BaseChartFragment() {

    //region attributes

    override val layoutId = R.layout.screen_chart_view

    override val chartContainerId = R.id.chartContainer

    override val settingsJsonPath = "chart_settings.json"

    //endregion

    //region Lifecycle

    override fun onViewReady() {
        super.onViewReady()

        title.text = getString(R.string.title_chart_view_label)

        chartSelector?.delegate = object: OnChangeListener {
            override fun didChangeChartType(type: ChartType) {
                //myOwnChart.currentType = type
                updateChartType(type)
            }
        }

        activity!!.loadJsonObject<ChartSetting>(settingsJsonPath)?.let {
            chartSelector.currentTypeSelected = ChartType.valueOf(it.type)
        }

        repeat(20) {
            getData()
        }
    }

    //endregion

    //region Helpers

    //TODO: Must delegate action to another layer component (ViewModel or Presenter)
    private fun getData() {
        ChartFakeRepository().getData(false,
            object : SingleItemCallback<ChartEntry> {
                override fun onServerError(message: String) {
                    Log.d("tag", message)
                }

                override fun onLoad(item: ChartEntry?) {
                    item?.let {
                        //myOwnChart.entries = item
                        updateDataSet(item)
                    }
                }
        })
    }

    //endregion

}
