package mx.yellowme.youst.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewHolderDecorable<Model>(
    itemView: View
) : RecyclerView.ViewHolder(itemView), Decorable<Model>
