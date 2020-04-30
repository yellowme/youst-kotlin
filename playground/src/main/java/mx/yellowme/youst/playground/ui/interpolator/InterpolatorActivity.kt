package mx.yellowme.youst.playground.ui.interpolator

import android.os.Bundle
import kotlinx.android.synthetic.main.interpolator.easeCardView
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.ui.widgets.ResizableCardView.Interpolator.EASE

class InterpolatorActivity : BaseActivity() {

    override val layoutId = R.layout.interpolator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(easeCardView) {
            interpolator = EASE
            duration = 600
        }
    }
}
