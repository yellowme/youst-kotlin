package mx.yellowme.youst.core.extensions

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation

/**
 * aView.blink(3)  // Blink 3 times
 * aView.blink()  // Just keep blinking
 */
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

fun fadeIn(duration: Long = 200): Animation {
    val animation = AlphaAnimation(0f, 1f)
    animation.duration = duration
    return commonFade(animation)
}

fun fadeOut(duration: Long = 200): Animation {
    val animation = AlphaAnimation(1f, 0f)
    animation.duration = duration
    return commonFade(animation)
}

fun fadeInFromBottom(duration: Long = 200, sizePercentile: Float = 0.03f): Animation {
    return fadeInTranslateY(
        duration,
        getScreenHeight() * sizePercentile
    )
}

fun fadeInFromLeft(duration: Long = 200, sizePercentile: Float = 0.03f): Animation {
    return fadeInTranslateX(
        duration,
        -(getScreenHeight() * sizePercentile)
    )
}

private fun fadeInTranslateX(duration: Long = 200, fromXDelta: Float): AnimationSet {
    val translateUp = TranslateAnimation(
        fromXDelta, 0f,
        0f, 0f
    )
    translateUp.duration = duration
    translateUp.repeatMode = Animation.REVERSE
    return commonFadeInTranslate(translateUp, duration)
}

private fun fadeInTranslateY(duration: Long = 200, fromYDelta: Float): AnimationSet {
    val translateUp = TranslateAnimation(
        0f, 0f,
        fromYDelta, 0f
    )
    translateUp.duration = duration
    translateUp.repeatMode = Animation.REVERSE
    return commonFadeInTranslate(translateUp, duration)
}

private fun commonFadeInTranslate(translate: Animation, duration: Long = 200): AnimationSet {
    val fadeTranslate = AnimationSet(true)
    fadeTranslate.addAnimation(translate)
    fadeTranslate.addAnimation(fadeIn(duration / 2))
    fadeTranslate.fillAfter = true
    return fadeTranslate
}

/**
 * Helper method to avoid duplicated fade in/out setup.
 * @param fadeAnimation created with the proper in or out config
 * @return a fully build fade animation.
 */
private fun commonFade(fadeAnimation: Animation): Animation {
    fadeAnimation.fillAfter = true
    fadeAnimation.interpolator = AccelerateDecelerateInterpolator()
    return fadeAnimation
}
