package mx.yellowme.youst.playground.ui.chart.screens

import android.util.Log
import kotlinx.android.synthetic.main.screen_chart_view.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.core.utils.loadJsonObject
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.OnChangeListener
import mx.yellowme.youst.playground.data.ChartFakeRepository
import mx.yellowme.youst.playground.data.SingleItemCallback
import mx.yellowme.youst.playground.domain.ChartEntry
import mx.yellowme.youst.playground.domain.ChartSetting
import mx.yellowme.youst.playground.domain.ChartType

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
class ChartViewFragment : BaseFragment() {

    //region attributes

    override val layoutId = R.layout.screen_chart_view

    private val settingsJsonPath = "chart_settings.json"

    //endregion

    //region Lifecycle

    override fun onViewReady() {
        activity?.loadJsonObject<ChartSetting>(settingsJsonPath)?.let {
            yellowChart?.run {
                label = getString(R.string.showcases_label)
                chartSettings = it
            }

            chartSelector?.run {
                currentTypeSelected = ChartType.valueOf(it.type)
                delegate = object : OnChangeListener {
                    override fun didChangeChartType(type: ChartType) {
                        yellowChart?.updateType(type.toString())
                    }
                }
            }
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
                        yellowChart?.addData(item)
                    }
                }
        })
    }

    //endregion

}
