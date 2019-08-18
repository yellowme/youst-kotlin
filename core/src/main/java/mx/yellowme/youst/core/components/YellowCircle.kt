package mx.yellowme.youst.core.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import mx.yellowme.youst.core.R

class YellowCircle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        setBackgroundResource(R.drawable.component_circle_yellow_bg)
    }
}