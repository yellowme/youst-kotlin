package mx.yellowme.youst.common.activities;

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.R
import mx.yellowme.youst.common.RecyclerViewHolderDecorable
import mx.yellowme.youst.common.SimpleRecyclerAdapter
import mx.yellowme.youst.utils.findOrThrow

/**
 * TODO: Add javadoc
 */
abstract class ChallengeWithListActivity<Model, ViewHolder : RecyclerViewHolderDecorable<Model>> : BaseChallengeActivity() {

    private var adapter: SimpleRecyclerAdapter<Model, ViewHolder>? = null

    private var progressBar: ProgressBar? = null
    private var emptyMessageView: TextView? = null

    private lateinit var recyclerView: RecyclerView

    /**
     * TODO: Add docs
     */
    protected abstract fun initAdapter(): SimpleRecyclerAdapter<Model, ViewHolder>?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    enum class ListState {
        LOADING, EMPTY, LOADED
    }

    fun setState(state: ListState) {
        when (state) {
            ListState.LOADING -> {
                progressBar?.visibility = VISIBLE
                emptyMessageView?.visibility = GONE
                recyclerView.visibility = GONE
            }
            ListState.EMPTY -> {
                progressBar?.visibility = VISIBLE
                emptyMessageView?.visibility = GONE
                recyclerView.visibility = GONE
            }
            ListState.LOADED -> {
                progressBar?.visibility = GONE
                emptyMessageView?.visibility = GONE
                recyclerView.visibility = VISIBLE
            }
        }
    }

    fun display(items: MutableList<Model>?) {
        items?.let {
            if (it.isEmpty()) {
                setState(ListState.EMPTY)
                return
            }

            adapter?.replaceData(it)
        }
    }

    override fun bindViews() {
        super.bindViews()
        progressBar = findViewById(R.id.loadingProgressBar)
        emptyMessageView = findViewById(R.id.emptyMessageTextView)
        recyclerView = findOrThrow(R.id.recyclerView)
    }

    /**
     * TODO: Add docs
     */
    protected open fun initLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this)
    }

    /**
     * TODO: Add docs
     */
    private fun setupRecyclerView() {
        adapter = initAdapter() ?: throw RuntimeException("Adapter should not be null when calling 'initAdapter'")

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = initLayoutManager()
        recyclerView.adapter = adapter
    }
}
