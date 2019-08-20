package mx.yellowme.youst.showcase

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.showcase_option_item.view.*
import mx.yellowme.youst.core.extensions.displayOrThrow
import mx.yellowme.youst.core.extensions.dp
import mx.yellowme.youst.core.hooks.recycler.*
import mx.yellowme.youst.core.hooks.setSingleOnClickListener

data class ShowcaseOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val hexColor: String
) {
    val optionId: ShowcaseOptionId
        get() {
            return ShowcaseOptionId.valueOf(id.toUpperCase())
        }
}

enum class ShowcaseOptionId {
    CHALLENGES,
    PLAYGROUND,
    MINI_APPS,
    PORTFOLIO
}

class ShowcaseOptionRecyclerView<ActivityListener>(
    private val recyclerView: RecyclerView,
    private val activityListener: ActivityListener
) where ActivityListener : Activity, ActivityListener : ItemListener<ShowcaseOption> {

    private var optionsAdapter: ShowcaseOptionAdapter

    init {
        with(recyclerView) {
            optionsAdapter = ShowcaseOptionAdapter(mutableListOf(), activityListener)
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

    fun setData(data: List<ShowcaseOption>?) {
        optionsAdapter.replaceData(data?.toMutableList() ?: mutableListOf())
        optionsAdapter.notifyDataSetChanged()
        recyclerView.invalidate()
    }

    data class Builder(var recyclerView: RecyclerView? = null) {
        fun with(recyclerView: RecyclerView) = apply {
            this.recyclerView = recyclerView
        }

        fun into(activity: ShowcaseActivity): ShowcaseOptionRecyclerView<ShowcaseActivity> {
            recyclerView?.let {
                return ShowcaseOptionRecyclerView(it, activity)
            } ?: throw RuntimeException("RecyclerView reference must not be null")
        }
    }
}

class ShowcaseOptionAdapter(
    items: MutableList<ShowcaseOption>,
    itemListener: ItemListener<ShowcaseOption>
) : SimpleRecyclerAdapter<ShowcaseOption, ShowcaseOptionViewHolder>(items, itemListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseOptionViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.showcase_option_item, parent, false)

        return ShowcaseOptionViewHolder(view, itemListener)
    }

}

class ShowcaseOptionViewHolder(
    itemView: View,
    private val listener: ItemListener<ShowcaseOption>? = null
) : RecyclerViewHolderDecorator<ShowcaseOption>(itemView) {
    override fun decorate(model: ShowcaseOption) {
        with(itemView) {
            setSingleOnClickListener { listener?.onItemClick(model) }
            titleTextView.displayOrThrow(model.title)
            subtitleTextView.displayOrThrow(model.subtitle)
            materialCardView.setCardBackgroundColor(Color.parseColor(model.hexColor))
        }
    }
}