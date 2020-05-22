package mx.yellowme.youst.playground

import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.templates.showcase.ModelTransformer
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.asJsonArrayOf
import mx.yellowme.youst.core.utils.intentTo
import mx.yellowme.youst.playground.domain.PlaygroundElement
import mx.yellowme.youst.playground.domain.PlaygroundElementType

class PlaygroundActivity : GenericShowcaseActivity<PlaygroundElement>() {
    override val titleResId: Int = R.string.playground_title

    override val subtitleResId: Int = R.string.playground_subtitle

    override val optionsJsonName: String = "playground.json"

    override val modelTransformer = object : ModelTransformer<PlaygroundElement> {
        override fun asList(rawString: String): List<PlaygroundElement> {
            return rawString.asJsonArrayOf()!!
        }
    }

    // TODO: Improve item handle
    override fun onItemClick(item: PlaygroundElement?) {
        item?.type?.let {
            when (it) {
                PlaygroundElementType.JETPACK_NAVIGATION -> {
                    launch(intentTo(Activities.Playground.Navigation))
                }
                PlaygroundElementType.NEMO -> {
                    launch(intentTo(Activities.Playground.Nemo))
                }
                PlaygroundElementType.CHART -> {
                    launch(intentTo(Activities.Playground.Chart))
                }
                PlaygroundElementType.BARCODE -> {
                    launch(intentTo(Activities.Playground.BARCODE))
                }
                PlaygroundElementType.INTERPOLATOR -> {
                    launch(intentTo(Activities.Playground.Interpolator))
                }
            }
        }
    }
}
