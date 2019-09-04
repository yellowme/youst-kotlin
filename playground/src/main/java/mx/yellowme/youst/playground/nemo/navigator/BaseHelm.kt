package mx.yellowme.youst.playground.nemo.navigator

interface BaseHelm {
    fun sendToLogin()

    fun sendToSplash()

    fun sendToUnsupportedVersion()

    fun sendToUserWithoutAttribute()

    fun sendToMissingPaymentOption()

    fun sendToMain()
}
