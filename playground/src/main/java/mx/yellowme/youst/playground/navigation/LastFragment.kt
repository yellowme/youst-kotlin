package mx.yellowme.youst.playground.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.navigation_destination_three.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.playground.R

class LastFragment : BaseFragment() {

    override val layoutId: Int = R.layout.navigation_destination_three

    private val args: LastFragmentArgs by navArgs()

    override fun setup() {
        goodbyeTextView.text = getString(R.string.navigation_title_step_3, args.nameArg)

        titleEmoji?.setOnClickListener {
            val action = LastFragmentDirections.backToGreeting()
            findNavController().navigate(action)
        }

        yellowmeLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yellowme.mx/"))
            startActivity(browserIntent)
        }
    }

}
