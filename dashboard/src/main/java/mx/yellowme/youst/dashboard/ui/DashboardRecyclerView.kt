package mx.yellowme.youst.dashboard.ui

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.dashboard.domain.DashboardOption

class DashboardRecyclerView<ActivityItemListener>(
    private val recyclerView: RecyclerView,
    private val activityListener: ActivityItemListener
) where ActivityItemListener : Activity, ActivityItemListener : ItemListener<DashboardOption> {
    private var optionsAdapter: DashboardOptionAdapter

    init {
        with(recyclerView) {
            optionsAdapter = DashboardOptionAdapter(
                mutableListOf(),
                activityListener
            )
            adapter = optionsAdapter
        }
    }

    fun setData(data: List<DashboardOption>?) {
        optionsAdapter.replaceData(data?.toMutableList() ?: mutableListOf())
        optionsAdapter.notifyDataSetChanged()
        recyclerView.invalidate()
    }

    data class Builder(var recyclerView: RecyclerView? = null) {
        fun with(recyclerView: RecyclerView) = apply {
            this.recyclerView = recyclerView
        }

        fun into(activity: DashboardActivity): DashboardRecyclerView<DashboardActivity> {
            recyclerView?.let {
                return DashboardRecyclerView(
                    it,
                    activity
                )
            } ?: throw RuntimeException("RecyclerView reference must not be null")
        }
    }
}
