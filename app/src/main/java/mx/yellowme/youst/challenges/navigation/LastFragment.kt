package mx.yellowme.youst.challenges.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.screen_destination_three.*
import mx.yellowme.youst.R

class LastFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        titleEmoji?.setOnClickListener {
            val action = LastFragmentDirections.backToGreeting()
            findNavController().navigate(action)
        }

        yellowmeLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yellowme.mx/"))
            startActivity(browserIntent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_destination_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: LastFragmentArgs by navArgs()
        goodbyeTextView.text = getString(R.string.navigation_title_step_3, args.nameArg)
    }

}