package mx.yellowme.youst.playground.navigation

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.screen_login.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener

class UnsupportedVersionFragment : BaseFragment() {

    override val layoutId: Int = R.layout.screen_unsupported

    override fun onViewReady() {
        appHero.listener = object : AppHeroActionListener {
            override fun onClickAction() {
                val action = UnsupportedVersionFragmentDirections.toLogin()
                findNavController().navigate(action)
            }
        }
    }

}
