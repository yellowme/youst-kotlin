package mx.yellowme.youst.miniapps.ui.contact

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.screen_destination_three.*
import mx.yellowme.youst.core.hooks.BaseFragment
import mx.yellowme.youst.core.utils.launchExternalEmailChooser
import mx.yellowme.youst.miniapps.R


class LastFragment : BaseFragment() {

    private val args: LastFragmentArgs by navArgs()

    override val layoutId = R.layout.screen_destination_three

    override fun onViewReady() {
        goodbyeTextView.text = getString(R.string.navigation_title_step_3, args.nameArg)

        titleEmoji?.setOnClickListener {
            val action = LastFragmentDirections.backToGreeting()
            findNavController().navigate(action)
        }

        yellowmeLink.setOnClickListener {
            activity?.launchExternalEmailChooser(
                getString(R.string.yellowme_email_address),
                getString(R.string.yellowme_email_subject)
            )
        }
    }


}
