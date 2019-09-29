package mx.yellowme.youst.playground.domain

/**
 * Created by adrianleyvasanchez on 29,September,2019
 */
enum class ChartType(name: String) {
    BAR("Bar"),
    LINE("Line"),
    BUBBLE( "Bubble"),
    UNDEFINED("undefined")
}

fun getTypeByName(name: String): ChartType {
    return when (name) {
        ChartType.BAR.name -> {
            ChartType.BAR
        }
        ChartType.LINE.name -> {
            ChartType.LINE
        }
        ChartType.BUBBLE.name -> {
            ChartType.BUBBLE
        }
        else -> {
            ChartType.UNDEFINED
        }
    }
}