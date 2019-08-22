package mx.yellowme.youst.playground.domain

data class Payment(var alias: String, var number: String, var isValid: Boolean = false)
