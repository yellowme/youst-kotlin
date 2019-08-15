@file:Suppress("unused")

package mx.yellowme.youst.core.hooks.animations

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.setListener(init: AnimationListenerHelper.() -> Unit) {
    val listener = AnimationListenerHelper()
    listener.init()
    addAnimatorListener(listener)
}

private typealias AnimListener = (Animator) -> Unit

class AnimationListenerHelper : Animator.AnimatorListener {
    private var animationStart: AnimListener? = null
    private var animationEnd: AnimListener? = null
    private var animationRepeat: AnimListener? = null
    private var animationCancel: AnimListener? = null

    //region Builder

    fun onAnimationStart(onAnimationCancel: AnimListener) {
        animationEnd = onAnimationCancel
    }

    fun onAnimationEnd(onAnimationEnd: AnimListener) {
        animationEnd = onAnimationEnd
    }

    fun onAnimationRepeat(onAnimationRepeat: AnimListener) {
        animationEnd = onAnimationRepeat
    }

    fun onAnimationCancel(onAnimationCancel: AnimListener) {
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