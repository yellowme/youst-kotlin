package mx.yellowme.youst.dashboard

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashboard_option_item.view.*
import mx.yellowme.youst.core.extensions.displayOrThrow
import mx.yellowme.youst.core.extensions.dp
import mx.yellowme.youst.core.hooks.recycler.*
import mx.yellowme.youst.core.hooks.setSingleOnClickListener

data class DashboardOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val hexColor: String
) {
    val optionId: DashboardOptionId
        get() {
            return DashboardOptionId.valueOf(id.toUpperCase())
        }
}

enum class DashboardOptionId {
    CHALLENGES,
    PLAYGROUND,
    MINI_APPS,
    PORTFOLIO
}

class DashboardOptionRecyclerView<ActivityListener>(
    private val recyclerView: RecyclerView,
    private val activityListener: ActivityListener
) where ActivityListener : Activity, ActivityListener : ItemListener<DashboardOption> {

    private var optionsAdapter: DashboardOptionAdapter

    init {
        with(recyclerView) {
            optionsAdapter = DashboardOptionAdapter(mutableListOf(), activityListener)
            adapter = optionsAdapter

            viewTreeObserver
                .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        val expectedPaddingSeparation = 8.dp
                        val calculatedHeight =
                            (height - expectedPaddingSeparation) / 2

                        layoutManager =
                            SpanningGridLayoutManager(
                                activityListener, 2, calculatedHeight
                            )

                        addItemDecoration(
                            GridSpacingItemDecoration(
                                2,
                                expectedPaddingSeparation,
                                false
                            )
                        )

                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
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

        fun into(activity: DashboardActivity): DashboardOptionRecyclerView<DashboardActivity> {
            recyclerView?.let {
                return DashboardOptionRecyclerView(it, activity)
            } ?: throw RuntimeException("RecyclerView reference must not be null")
        }
    }
}

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

class DashboardOptionViewHolder(
    itemView: View,
    private val listener: ItemListener<DashboardOption>? = null
) : RecyclerViewHolderDecorator<DashboardOption>(itemView) {
    override fun decorate(model: DashboardOption) {
        with(itemView) {
            setSingleOnClickListener { listener?.onItemClick(model) }
            titleTextView.displayOrThrow(model.title)
            subtitleTextView.displayOrThrow(model.subtitle)
            materialCardView.setCardBackgroundColor(Color.parseColor(model.hexColor))
        }
    }
}
