package mx.yellowme.youst.common.utils

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.setListener(init: AnimationListenerHelper.() -> Unit) {
    val listener = AnimationListenerHelper()
    listener.init()
    addAnimatorListener(listener)
}

class AnimationListenerHelper : Animator.AnimatorListener {
    private var animationStart: AnimListener? = null
    private var animationEnd: AnimListener? = null
    private var animationRepeat: AnimListener? = null
    private var animationCancel: AnimListener? = null

    fun onAnimationStart(onAnimationCancel: AnimListener) {
        animationEnd = onAnimationCancel
    }

    override fun onAnimationStart(animation: Animator) {
        animationStart?.invoke(animation)
    }

    fun onAnimationEnd(onAnimationEnd: AnimListener) {
        animationEnd = onAnimationEnd
    }

    override fun onAnimationEnd(animation: Animator) {
        animationEnd?.invoke(animation)
    }

    fun onAnimationRepeat(onAnimationRepeat: AnimListener) {
        animationEnd = onAnimationRepeat
    }

    override fun onAnimationRepeat(animation: Animator) {
        animationRepeat?.invoke(animation)
    }

    fun onAnimationCancel(onAnimationCancel: AnimListener) {
        animationEnd = onAnimationCancel
    }

    override fun onAnimationCancel(animation: Animator) {
        animationCancel?.invoke(animation)
    }
}

private typealias AnimListener = (Animator) -> Unit