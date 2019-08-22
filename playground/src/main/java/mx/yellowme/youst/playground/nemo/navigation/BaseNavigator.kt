package mx.yellowme.youst.playground.nemo.navigation

interface BaseNavigator {
    fun sendToLogin()

    fun sendToSplash()

    fun sendToUnsupportedVersion()

    fun sendToUserWithoutAttribute()

    fun sendToMissingPaymentOption()

    fun sendToMain()
}
