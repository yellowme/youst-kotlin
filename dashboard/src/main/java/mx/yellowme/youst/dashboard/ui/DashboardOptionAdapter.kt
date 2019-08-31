package mx.yellowme.youst.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter
import mx.yellowme.youst.dashboard.R
import mx.yellowme.youst.dashboard.domain.DashboardOption

class DashboardOptionAdapter(
    items: MutableList<DashboardOption>,
    itemListener: ItemListener<DashboardOption>
) : SimpleRecyclerAdapter<DashboardOption, DashboardOptionViewHolder>(items, itemListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardOptionViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.dashboard_option_item, parent, false)

        return DashboardOptionViewHolder(view, itemListener)
    }

}
