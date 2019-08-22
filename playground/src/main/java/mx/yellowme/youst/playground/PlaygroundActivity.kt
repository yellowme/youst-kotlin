package mx.yellowme.youst.playground

import mx.yellowme.youst.core.domain.GenericShowcasedOption
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.templates.showcase.DefaultModelTransformer
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.intentTo
import mx.yellowme.youst.core.R as coreR

class PlaygroundActivity : GenericShowcaseActivity<GenericShowcasedOption>() {

    override val titleResId: Int = R.string.playground_title

    override val subtitleResId: Int = R.string.playground_subtitle

    override val optionsJsonName: String = "playground.json"

    override val modelTransformer = DefaultModelTransformer()

    //TODO: Improve item handle
    override fun onItemClick(item: GenericShowcasedOption?) {
        item?.id?.let {
            when (it) {
                "1" -> {
                    launch(intentTo(Activities.Playground.Navigation))
                }
                "2" -> {
                    launch(intentTo(Activities.Playground.Nemo))
                }
                else -> throw RuntimeException("Invalid identifier")
            }
        }
    }

}
