package mx.yellowme.youst.playground.navigation

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.screen_login.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener

class MainFragment : BaseFragment() {

    //region Attributes

    override val layoutId: Int = R.layout.screen_main

    //endregion

    override fun onViewReady() {
        appHero.message = getString(
            R.string.screen_main,
            getString(R.string.userName),
            getString(R.string.paymentData)
        )

        appHero.listener = object : AppHeroActionListener {
            override fun onClickAction() {
                val action = MainFragmentDirections.toUnsupported()
                findNavController().navigate(action)
            }
        }
    }
}
