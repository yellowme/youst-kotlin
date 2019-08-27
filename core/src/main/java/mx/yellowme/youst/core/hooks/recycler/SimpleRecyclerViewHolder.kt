package mx.yellowme.youst.core.hooks.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * TODO: Add docs
 */
abstract class RecyclerViewHolderDecorator<Model>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun decorate(model: Model)
}
