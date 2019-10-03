package mx.yellowme.youst.playground.domain

/**
 * Created by adrianleyvasanchez on 03,October,2019
 */
data class ChartSetting(
    val type: String,
    val description: String,
    val descriptionTextSize: Float,
    val descriptionColor: String,
    val noDataText: String,
    val backgroundColor: String,
    val gridBackgroundColor: String,
    val borderColor: String,
    val borderWidth: Float,
    val maxVisibleValueCount: Int,
    val logEnabled: Boolean,
    val drawGridBackgroundEnabled: Boolean,
    val drawBarShadowEnabled: Boolean
)