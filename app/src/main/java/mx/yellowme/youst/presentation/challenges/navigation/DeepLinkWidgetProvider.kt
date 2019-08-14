package mx.yellowme.youst.presentation.challenges.navigation

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import mx.yellowme.youst.R

class DeepLinkWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        val remoteViews = RemoteViews(context.packageName, R.layout.deeplink_widget)

        val pendingIntent = NavDeepLinkBuilder(context)
                .setComponentName(NavigationActivity::class.java)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.deepLinkFragment)
                .createPendingIntent()

        remoteViews.setOnClickPendingIntent(R.id.deep_link_button, pendingIntent)
        appWidgetManager?.updateAppWidget(appWidgetIds, remoteViews)
    }
}