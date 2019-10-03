package mx.yellowme.youst.dashboard.ui

import android.view.View
import kotlinx.android.synthetic.main.dashboard_option_item.view.*
import mx.yellowme.youst.core.components.PaletteColors
import mx.yellowme.youst.core.extensions.displayOrThrow
import mx.yellowme.youst.core.extensions.toColor
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.RecyclerViewHolderDecorator
import mx.yellowme.youst.core.hooks.setSingleOnClickListener
import mx.yellowme.youst.dashboard.domain.DashboardOption

class DashboardOptionViewHolder(
    itemView: View,
    private val listener: ItemListener<DashboardOption>? = null
) : RecyclerViewHolderDecorator<DashboardOption>(itemView) {
    override fun decorate(model: DashboardOption) {
        with(itemView) {
            setSingleOnClickListener { listener?.onItemClick(model) }
            titleTextView.displayOrThrow(model.title)
            subtitleTextView.displayOrThrow(model.subtitle)
            materialCardView.setCardBackgroundColor(
                PaletteColors.valueOf(model.paletteColor).hexColor.toColor(context)
            )
        }
    }
}
