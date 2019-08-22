package mx.yellowme.youst.playground

import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.templates.showcase.GenericShowcasedOption
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.intentTo

class PlaygroundActivity : GenericShowcaseActivity() {

    override val titleResId: Int = R.string.playground_title

    override val subtitleResId: Int = R.string.playground_subtitle

    override val optionsJsonName: String = "playground.json"

    //TODO: Improve item handle
    override fun onItemClick(item: GenericShowcasedOption?) {
        item?.id?.let {
            when (it) {
                "1" -> {
                    launch(intentTo(Activities.Playground.Navigation))
                }
                else -> throw RuntimeException("Invalid challenge identifier")
            }
        }
    }

}
