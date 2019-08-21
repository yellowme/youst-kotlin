package mx.yellowme.youst.playground

import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.templates.showcase.GenericShowcaseActivity
import mx.yellowme.youst.core.templates.showcase.GenericShowcasedOption

class PlaygroundActivity : GenericShowcaseActivity() {

    override val titleResId: Int = R.string.playground_title

    override val subtitleResId: Int = R.string.playground_subtitle

    override val optionsJsonName: String = "playground.json"

    //TODO: Improve item handle
    override fun onItemClick(item: GenericShowcasedOption?) {
        item?.id?.let {
            toast("Must implement for ${item.id}")

            /*
            val nextActivity: Class<*> = when (it) {
                "1" -> {
                    throw RuntimeException("Invalid challenge identifier")
                }
                "2" -> {
                    throw RuntimeException("Invalid challenge identifier")
                }
                else -> throw RuntimeException("Invalid challenge identifier")
            }

            launch(nextActivity) {
                putExtra(TOOLBAR_TITLE, item.title)
            }*/
        }
    }

}
