package mx.yellowme.youst.playground.navigation

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.navigation_destination_two.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R

class MiddleFragment : BaseFragment() {

    //region Attributes

    override val layoutId: Int = R.layout.navigation_destination_two

    private val args: MiddleFragmentArgs by navArgs()

    //endregion

    override fun setup() {
        destinationTextView.text = getString(R.string.navigation_title_step_2, args.textArg)

        navigateButton?.setOnClickListener {
            val action = MiddleFragmentDirections.toLastFragment(args.textArg)
            findNavController().navigate(action)
        }
    }

}
