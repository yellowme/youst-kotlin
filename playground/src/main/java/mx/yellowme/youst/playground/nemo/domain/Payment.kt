package mx.yellowme.youst.playground.nemo.domain

data class Payment(var alias: String, var number: String, var isValid: Boolean = false)
