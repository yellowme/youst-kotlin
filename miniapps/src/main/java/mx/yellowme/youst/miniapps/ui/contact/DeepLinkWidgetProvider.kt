package mx.yellowme.youst.miniapps.ui.contact

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import mx.yellowme.youst.miniapps.R
import mx.yellowme.youst.R as appR

class DeepLinkWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val remoteViews = RemoteViews(context.packageName, appR.layout.component_deep_link)

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(ContactUsActivity::class.java)
            .setGraph(R.navigation.contact_us_navigation_graph)
            .setDestination(R.id.deepLinkFragment)
            .createPendingIntent()

        remoteViews.setOnClickPendingIntent(appR.id.deep_link_button, pendingIntent)
        appWidgetManager?.updateAppWidget(appWidgetIds, remoteViews)
    }
}
