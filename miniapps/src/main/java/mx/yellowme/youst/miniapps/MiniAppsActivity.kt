package mx.yellowme.youst.miniapps

import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.templates.showcase.ModelTransformer
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.asJsonArrayOf
import mx.yellowme.youst.core.utils.intentTo
import mx.yellowme.youst.miniapps.domain.MiniAppElement
import mx.yellowme.youst.miniapps.domain.MiniAppElementType

class MiniAppsActivity : GenericShowcaseActivity<MiniAppElement>() {

    override val titleResId = R.string.mini_apps_title

    override val subtitleResId = R.string.mini_apps_subtitle

    override val optionsJsonName = "miniapps.json"

    override val modelTransformer = object : ModelTransformer<MiniAppElement> {
        override fun asList(rawString: String): List<MiniAppElement> {
            return rawString.asJsonArrayOf()!!
        }
    }

    override fun onItemClick(item: MiniAppElement?) {
        item?.type?.let {
            when (it) {
                MiniAppElementType.CHORDS -> {
                    launch(intentTo(Activities.MiniApps.Chords))
                }
                MiniAppElementType.CONTACT_US -> {
                    launch(intentTo(Activities.MiniApps.ContactUs))
                }
                else -> throw RuntimeException("Invalid type")
            }
        }
    }

}
