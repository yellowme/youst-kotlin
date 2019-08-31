package mx.yellowme.youst.dashboard.ui

import android.app.Activity
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.core.extensions.dp
import mx.yellowme.youst.core.hooks.recycler.GridSpacingItemDecoration
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SpanningGridLayoutManager
import mx.yellowme.youst.dashboard.domain.DashboardOption

class DashboardOptionRecyclerView<ActivityItemListener>(
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
                return DashboardOptionRecyclerView(
                    it,
                    activity
                )
            } ?: throw RuntimeException("RecyclerView reference must not be null")
        }
    }

}
