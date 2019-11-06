package mx.yellowme.youst.playground.ui.chart

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_chart.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener
import mx.yellowme.youst.playground.ui.chart.screens.ChartViewFragment

/**
 * @author adrianleyvasanchez
 * @since 27,September,2019
 */
class ChartActivity : BaseActivity() {

    //Region attributes

    override val layoutId = R.layout.screen_chart

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appHero.run {
            message = getString(R.string.screen_chart)
            mainActionListener = object : AppHeroActionListener {
                override fun onClickAction() {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(android.R.id.content, ChartViewFragment())
                        .commit()
                }
            }
        }
    }

    //endregion

}