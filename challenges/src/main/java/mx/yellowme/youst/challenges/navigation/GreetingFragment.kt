package mx.yellowme.youst.challenges.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.navigation_destination_one.*
import mx.yellowme.youst.challenges.R

class GreetingFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigateButton?.setOnClickListener {
            val action =
                GreetingFragmentDirections.toSecondDestination(nameEditText.text.toString())
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.navigation_destination_one, container, false)
    }

}
