package mx.yellowme.youst.playground.ui.nemo.navigator.hooks

import mx.yellowme.youst.playground.BuildConfig

abstract class Navigator {
    var next: Navigator? = null

    private val tagName: String
        get() {
            return javaClass.simpleName
        }

    abstract fun run()

    abstract fun executeExit()

    /**
     * Builds chains of handler objects.
     */
    fun linkWith(next: Navigator): Navigator {
        if (BuildConfig.DEBUG) {
            println("NAVIGATION: Link $tagName with next ${next.tagName}")
        }
        this.next = next
        return next
    }

    fun continueFlowIf(matchesValidations: Boolean) {
        next?.let {
            if (matchesValidations) {
                println("NAVIGATION: continue at $tagName, running next -> ${it.tagName}")
                it.run()
            } else {
                executeExit()
            }
        } ?: executeExit()
    }
}
