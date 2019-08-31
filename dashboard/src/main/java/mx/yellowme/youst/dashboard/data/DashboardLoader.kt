package mx.yellowme.youst.dashboard.data

import android.app.Activity
import mx.yellowme.youst.core.utils.loadJsonObject
import mx.yellowme.youst.dashboard.domain.Dashboard

object DashboardLoader {
    fun loadData(activity: Activity, completion: (Dashboard) -> Unit) {
        activity.loadJsonObject<Dashboard>("dashboard.json")?.let {
            completion(it)
        } ?: throw RuntimeException("Reading corrupted dashboard JSON file")
    }
}
