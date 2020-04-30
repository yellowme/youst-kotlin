package mx.yellowme.youst.core.hooks.animations

import android.view.animation.Animation

private typealias AnimationListener = (Animation) -> Unit

fun Animation.setListener(init: AnimationListenerHelper.() -> Unit) {
    val listener = AnimationListenerHelper()
    listener.init()
    setAnimationListener(listener)
}

class AnimationListenerHelper : Animation.AnimationListener {
    private var animationStart: AnimationListener? = null
    private var animationEnd: AnimationListener? = null
    private var animationRepeat: AnimationListener? = null

    //region Builder

    fun onAnimationStart(onAnimationStart: AnimationListener) {
        animationStart = onAnimationStart
    }

    fun onAnimationEnd(onAnimationEnd: AnimationListener) {
        animationEnd = onAnimationEnd
    }

    fun onAnimationRepeat(onAnimationRepeat: AnimationListener) {
        animationRepeat = onAnimationRepeat
    }

    //endregion

    //region AnimationListener

    override fun onAnimationStart(animation: Animation) {
        animationStart?.invoke(animation)
    }

    override fun onAnimationEnd(animation: Animation) {
        animationEnd?.invoke(animation)
    }

    override fun onAnimationRepeat(animation: Animation) {
        animationRepeat?.invoke(animation)
    }

    //endregion

}
