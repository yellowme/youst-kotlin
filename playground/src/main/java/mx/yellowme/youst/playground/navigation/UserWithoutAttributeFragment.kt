package mx.yellowme.youst.playground.navigation

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.screen_user_no_attribute.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.components.AppHeroActionListener

class UserWithoutAttributeFragment : BaseFragment() {

    override val layoutId: Int = R.layout.screen_user_no_attribute

    override fun onViewReady() {
        appHero.listener = object : AppHeroActionListener {
            override fun onClickAction() {
                val action = UserWithoutAttributeFragmentDirections.toPayment()
                findNavController().navigate(action)
            }
        }
    }

}
