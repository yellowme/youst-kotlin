package mx.yellowme.youst.playground.ui.chart

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.screen_chart.*
import mx.yellowme.youst.core.hooks.BaseActivity
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
 * @since 27,September,2019
 */
class ChartActivity : BaseActivity() {

    //Region Attributes

    override val layoutId = R.layout.screen_chart

    private val settingsJsonPath = "chart_settings.json"

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadJsonObject<ChartSetting>(settingsJsonPath)?.let {
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
