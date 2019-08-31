package mx.yellowme.youst.dashboard.domain

data class Dashboard(
    val title: String,
    val subtitle: String,
    val options: List<DashboardOption>
)
