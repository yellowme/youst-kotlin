package mx.yellowme.youst.dashboard

import android.os.Bundle
import kotlinx.android.synthetic.main.dashboard.*
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.intentTo
import mx.yellowme.youst.core.utils.loadJsonArray
import mx.yellowme.youst.core.R as coreR

class DashboardActivity : BaseActivity(), ItemListener<DashboardOption> {

    //region Attribute

    override val layoutId = R.layout.dashboard

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = DashboardOptionRecyclerView.Builder()
            .with(dashboardOptions)
            .into(this)


        loadJsonArray<DashboardOption>("dashboard.json")?.let {
            recyclerView.setData(it)
        } ?: throw RuntimeException("Reading corrupted dashboard JSON file")
    }

    override fun onItemClick(item: DashboardOption?) {
        when (item!!.optionId) {
            DashboardOptionId.CHALLENGES -> {
                launch(intentTo(Activities.Challenges))
            }
            DashboardOptionId.PLAYGROUND -> {
                launch(intentTo(Activities.Playground))
            }
            DashboardOptionId.MINI_APPS,
            DashboardOptionId.PORTFOLIO -> {
                toast(getString(coreR.string.work_in_progress))
            }
        }
    }
}
