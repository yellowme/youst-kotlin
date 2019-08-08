package mx.yellowme.youst.presentation.challenges.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.screen_destination_one.navigateButton
import kotlinx.android.synthetic.main.screen_destination_two.*
import mx.yellowme.youst.R

class MiddleFragment : Fragment() {

    val args: MiddleFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigateButton?.setOnClickListener {
            val action = MiddleFragmentDirections.toLastFragment(args.textArg)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_destination_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        destinationTextView.text = getString(R.string.navigation_title_step_2, args.textArg)
    }
}