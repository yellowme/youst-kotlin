package mx.yellowme.youst.playground.navigation

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.navigation_destination_one.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R

class GreetingFragment : BaseFragment() {

    override val layoutId: Int = R.layout.navigation_destination_one

    override fun setup() {
        navigateButton?.setOnClickListener {
            val action =
                GreetingFragmentDirections.toSecondDestination(nameEditText.text.toString())
            findNavController().navigate(action)
        }
    }

}
