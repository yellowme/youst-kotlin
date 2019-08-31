package mx.yellowme.youst.dashboard.domain

import java.util.*

data class DashboardOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val paletteColor: String
) {
    val optionId: DashboardOptionId
        get() {
            return DashboardOptionId.valueOf(id.toUpperCase(Locale.ROOT))
        }
}
