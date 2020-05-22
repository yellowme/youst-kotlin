package mx.yellowme.youst.core.hooks

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
        println("NAVIGATION: Link $tagName with next ${next.tagName}")
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
