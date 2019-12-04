package mx.yellowme.youst.challenges.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.component_generic_list_model.view.*
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.core.extensions.gone
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.core.extensions.visible
import mx.yellowme.youst.core.hooks.recycler.RecyclerViewHolderDecorator
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter

enum class GenericListState {
    LOADING, EMPTY, LOADED, ERROR
}

/**
 * @author adrianleyvasanchez
 * @since 03,December,2019
 */
abstract class GenericListModel<Model, ViewHolder : RecyclerViewHolderDecorator<Model>>
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    //region Attributes

    private var adapter: SimpleRecyclerAdapter<Model, ViewHolder>? = null

    protected abstract fun initAdapter(): SimpleRecyclerAdapter<Model, ViewHolder>?

    //endregion

    //region Setup

    init {
        inflate(R.layout.component_generic_list_model, context)
        setupRecyclerView()
    }

    //endregion

    //region Helpers

    fun setState(state: GenericListState) {
        when (state) {
            GenericListState.LOADING -> {
                loadingProgressBar.visible()

                emptyMessageTextView.gone()
                errorMessageTextView.gone()
                recyclerView.gone()
            }
            GenericListState.EMPTY -> {
                emptyMessageTextView.visible()

                loadingProgressBar.gone()
                errorMessageTextView.gone()
                recyclerView.gone()
            }
            GenericListState.LOADED -> {
                recyclerView.visible()

                loadingProgressBar.gone()
                emptyMessageTextView.gone()
                errorMessageTextView.gone()
            }
            GenericListState.ERROR -> {
                errorMessageTextView.visible()

                loadingProgressBar.gone()
                emptyMessageTextView.gone()
                recyclerView.gone()
            }
        }
    }

    fun display(items: MutableList<Model>?) {
        items?.let {
            if (it.isEmpty()) {
                setState(GenericListState.EMPTY)
                return
            }

            adapter?.replaceData(it)
        }
    }

    /**
     * TODO: Add docs
     */
    protected open fun initLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    /**
     * TODO: Add docs
     */
    private fun setupRecyclerView() {
        adapter = initAdapter()
            ?: throw RuntimeException("Adapter should not be null when calling 'initAdapter'")
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = initLayoutManager()
        recyclerView.adapter = adapter
    }

    //endregion
}
