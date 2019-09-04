package mx.yellowme.youst.playground.navigation

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.screen_login.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener

class ScreenBFragment : BaseFragment() {

    override val layoutId: Int = R.layout.screen_b

    override fun onViewReady() {

        with(appHero) {

            mainActionListener = object : AppHeroActionListener {
                override fun onClickAction() {
                    val action = ScreenBFragmentDirections.actionScreenBToScreenC()
                    findNavController().navigate(action)
                }
            }

            secondaryActionListener = object : AppHeroActionListener {
                override fun onClickAction() {
                    val action = ScreenBFragmentDirections.actionScreenBToScreenD()
                    findNavController().navigate(action)
                }
            }

        }

    }
}
