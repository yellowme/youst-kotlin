package mx.yellowme.youst.playground.ui.widgets

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.res.getResourceIdOrThrow
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.resizable_card_view.view.cardDescription
import kotlinx.android.synthetic.main.resizable_card_view.view.cardTitle
import kotlinx.android.synthetic.main.resizable_card_view.view.header
import mx.yellowme.youst.core.extensions.consumeTypeArray
import mx.yellowme.youst.core.extensions.dp
import mx.yellowme.youst.core.extensions.fadeIn
import mx.yellowme.youst.core.extensions.fadeOut
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.core.hooks.animations.setListener
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.ui.widgets.ResizableCardView.Interpolator.EASE
import mx.yellowme.youst.playground.ui.widgets.ResizableCardView.Interpolator.LINEAR
import mx.yellowme.youst.playground.ui.widgets.ResizableCardView.Interpolator.valueOf

/**
 * Custom implementation of a [MaterialCardView] that expands and contracts with a click event.
 * The duration of the expansion animation, as well as the value interpolation, are customizable.
 * The interpolator can be either linear or ease.
 */
class ResizableCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {
    var duration: Long =
        DEFAULT_DURATION.toLong()
    lateinit var interpolator: Interpolator
    var cardBackground: Int = R.drawable.blue_gradient
        set(backgroundResource) {
            updateHeaderBackground(backgroundResource)
        }
    private var title: String = ""
        set(text) {
            field = text
            cardTitle.text = text
        }
    private var description: String = ""
        set(text) {
            field = text
            cardDescription.text = text
        }
    private var isAnimationFinished = true
    private var isExpanded = false

    init {
        initCardView()
        setSizeChangeAnimation()
        attrs?.consumeTypeArray(context, R.styleable.ResizableCardView) {
            title = getString(R.styleable.ResizableCardView_resizableCardTitle) ?: ""
            description = getString(R.styleable.ResizableCardView_resizableCardDescription) ?: ""
            interpolator =
                valueOf(
                    getString(R.styleable.ResizableCardView_resizableCardInterpolator)
                        ?: LINEAR.name
                )
            duration =
                getInt(R.styleable.ResizableCardView_expandDuration, DEFAULT_DURATION).toLong()
            cardBackground =
                getResourceIdOrThrow(R.styleable.ResizableCardView_resizableCardBackground)
        }
    }

    private fun setSizeChangeAnimation() {
        setOnClickListener {
            val cardWidth = width
            val cardHeight = height
            val cornerRadius = radius

            val widthAnimator =
                ValueAnimator.ofInt(cardWidth, cardWidth + if (isExpanded) (-80).dp else 80.dp)
                    .apply {
                        addUpdateListener { animator ->
                            val value = animator.animatedValue as Int
                            layoutParams.width = value
                            requestLayout()
                        }
                    }

            val heightAnimator =
                ValueAnimator.ofInt(cardHeight, cardHeight + if (isExpanded) (-80).dp else 80.dp)
                    .apply {
                        addUpdateListener { animator ->
                            val value = animator.animatedValue as Int
                            layoutParams.height = value
                            requestLayout()
                        }
                    }

            val cornerAnimator =
                ValueAnimator.ofFloat(
                    cornerRadius,
                    cornerRadius + if (isExpanded) 6.dp.toFloat() else (-6).dp.toFloat()
                )
                    .apply {
                        addUpdateListener { animator ->
                            val value = animator.animatedValue as Float
                            radius = value
                            requestLayout()
                        }
                    }

            AnimatorSet().apply {
                if (isAnimationFinished) {
                    playTogether(widthAnimator, heightAnimator, cornerAnimator)
                    interpolator = when (this@ResizableCardView.interpolator) {
                        LINEAR -> LinearInterpolator()
                        EASE -> AnimationUtils.loadInterpolator(context, R.anim.ease)
                    }
                    duration = this@ResizableCardView.duration
                    doOnStart {
                        isAnimationFinished = false
                    }
                    doOnEnd {
                        cardDescription.startAnimation(fadeIn())
                        isExpanded = !isExpanded
                        isAnimationFinished = true
                    }
                    startCardAnimation()
                }
            }
        }
    }

    private fun initCardView() {
        inflate(R.layout.resizable_card_view, context)
        cardElevation = 20.dp.toFloat()
        updateHeaderBackground(cardBackground)
    }

    private fun updateHeaderBackground(id: Int) {
        header.background = context.getDrawable(id)
    }

    private fun AnimatorSet.startCardAnimation() {
        val descriptionFadeOut = fadeOut().apply {
            setListener {
                onAnimationEnd {
                    this@startCardAnimation.start()
                }
            }
        }
        cardDescription.startAnimation(descriptionFadeOut)
    }

    enum class Interpolator {
        LINEAR, EASE
    }

    companion object {
        const val DEFAULT_DURATION = 500
    }
}
