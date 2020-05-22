package mx.yellowme.youst.miniapps.ui.contact

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.screen_destination_one.nameEditText
import kotlinx.android.synthetic.main.screen_destination_one.navigateButton
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.miniapps.R

class GreetingFragment : BaseFragment() {
    override val layoutId: Int = R.layout.screen_destination_one

    override fun onViewReady() {
        navigateButton?.setOnClickListener {
            val action =
                GreetingFragmentDirections.toSecondDestination(nameEditText.text.toString())
            findNavController().navigate(action)
        }
    }
}
