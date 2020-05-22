package mx.yellowme.youst.core.hooks.recycler

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import mx.yellowme.youst.core.extensions.dp

class AutoSizeGridRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private val spanCount = 2

    init {
        viewTreeObserver
            .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val expectedPaddingSeparation = 8.dp
                    val calculatedHeight =
                        (height - expectedPaddingSeparation) / spanCount

                    layoutManager =
                        SpanningGridLayoutManager(
                            context, spanCount, calculatedHeight
                        )

                    addItemDecoration(
                        GridSpacingItemDecoration(
                            spanCount,
                            expectedPaddingSeparation,
                            false
                        )
                    )

                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
    }
}
