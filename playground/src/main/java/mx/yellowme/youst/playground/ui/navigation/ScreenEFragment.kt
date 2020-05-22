package mx.yellowme.youst.playground.ui.navigation

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.screen_user_no_attribute.appHero
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener

class ScreenEFragment : BaseFragment() {
    override val layoutId: Int = R.layout.screen_e

    override fun onViewReady() {
        appHero.mainActionListener = object : AppHeroActionListener {
            override fun onClickAction() {
                val action =
                    ScreenEFragmentDirections.actionScreenEToScreenF()
                findNavController().navigate(action)
            }
        }
    }
}
