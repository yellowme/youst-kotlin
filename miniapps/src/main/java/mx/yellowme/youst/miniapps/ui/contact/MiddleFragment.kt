package mx.yellowme.youst.miniapps.ui.contact

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.screen_destination_two.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.miniapps.R

class MiddleFragment : BaseFragment() {

    private val args: MiddleFragmentArgs by navArgs()

    override val layoutId = R.layout.screen_destination_two

    override fun onViewReady() {
        navigateButton?.setOnClickListener {
            val action = MiddleFragmentDirections.toLastFragment(args.textArg)
            findNavController().navigate(action)
        }

        destinationTextView.text = getString(R.string.navigation_title_step_2, args.textArg)
    }

}
