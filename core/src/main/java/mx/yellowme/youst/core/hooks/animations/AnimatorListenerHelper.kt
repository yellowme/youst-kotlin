@file:Suppress("unused")

package mx.yellowme.youst.core.hooks.animations

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.setListener(init: AnimatorListenerHelper.() -> Unit) {
    val listener = AnimatorListenerHelper()
    listener.init()
    addAnimatorListener(listener)
}

private typealias AnimatorListener = (Animator) -> Unit

class AnimatorListenerHelper : Animator.AnimatorListener {
    private var animationStart: AnimatorListener? = null
    private var animationEnd: AnimatorListener? = null
    private var animationRepeat: AnimatorListener? = null
    private var animationCancel: AnimatorListener? = null

    //region Builder

    fun onAnimationStart(onAnimationStart: AnimatorListener) {
        animationEnd = onAnimationStart
    }

    fun onAnimationEnd(onAnimationEnd: AnimatorListener) {
        animationEnd = onAnimationEnd
    }

    fun onAnimationRepeat(onAnimationRepeat: AnimatorListener) {
        animationEnd = onAnimationRepeat
    }

    fun onAnimationCancel(onAnimationCancel: AnimatorListener) {
        animationEnd = onAnimationCancel
    }

    //endregion

    //region AnimatorListener

    override fun onAnimationStart(animation: Animator) {
        animationStart?.invoke(animation)
    }

    override fun onAnimationEnd(animation: Animator) {
        animationEnd?.invoke(animation)
    }

    override fun onAnimationRepeat(animation: Animator) {
        animationRepeat?.invoke(animation)
    }

    override fun onAnimationCancel(animation: Animator) {
        animationCancel?.invoke(animation)
    }

    //endregion
}
