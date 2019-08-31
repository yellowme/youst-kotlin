package mx.yellowme.youst.playground.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.getResourceIdOrThrow
import kotlinx.android.synthetic.main.component_app_hero.view.*
import mx.yellowme.youst.core.extensions.*
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.core.R as coreR

interface AppHeroActionListener {
    fun onClickAction()
}

class AppHero @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var listener: AppHeroActionListener? = null

    var message: String? = null
        set(value) {
            field = value
            mainLabel.hideOrDisplay(message, false)
        }

    private var actionLabel: String? = null
        set(value) {
            field = value
            mainAction.hideOrDisplay(actionLabel, true)
        }

    private var backgroundColorRes: Int = -1
        set(value) {
            field = value
            if (backgroundColorRes != -1) {
                mainContainer.setBackgroundColor(
                    ContextCompat.getColor(context, backgroundColorRes)
                )
            }
        }

    private var messageColorRes: Int = -1
        set(value) {
            field = value
            if (messageColorRes != -1) {
                mainLabel.setTextColor(
                    ContextCompat.getColor(context, messageColorRes)
                )
            }
        }

    init {
        inflate(R.layout.component_app_hero, context)
        attrs?.consumeTypeArray(context, R.styleable.AppHero) {
            backgroundColorRes = getResourceIdOrThrow(R.styleable.AppHero_heroBackgroundColor)
            message = getString(R.styleable.AppHero_heroMessage)
            messageColorRes = getResourceId(R.styleable.AppHero_heroMessageColor, coreR.color.white)
            actionLabel = getString(R.styleable.AppHero_heroActionLabel)
        }

        mainAction.setOnClickListener {
            listener?.onClickAction()
        }
    }

    //TODO: Extract interface and use states
    fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            mainLabel.blink()
            mainAction.gone()
        } else {
            mainAction.visible()
            mainLabel.clearAnimation()
        }
    }

}

fun View.blink(
    times: Int = Animation.INFINITE,
    duration: Long = 400L,
    offset: Long = 0L,
    minAlpha: Float = 0.35f,
    maxAlpha: Float = 1.0f,
    repeatMode: Int = Animation.REVERSE
) {
    startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
        it.duration = duration
        it.startOffset = offset
        it.repeatMode = repeatMode
        it.repeatCount = times
    })
}
