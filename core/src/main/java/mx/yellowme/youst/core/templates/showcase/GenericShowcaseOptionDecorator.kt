package mx.yellowme.youst.core.templates.showcase

import android.view.View
import androidx.emoji.widget.EmojiAppCompatTextView
import com.google.android.material.card.MaterialCardView
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.components.PaletteColors
import mx.yellowme.youst.core.domain.GenericShowcasedOption
import mx.yellowme.youst.core.extensions.toColor
import mx.yellowme.youst.core.hooks.adapter.Decorator

class GenericShowcaseOptionDecorator<Model : GenericShowcasedOption> : Decorator<Model>() {
    override fun decorate(model: Model?, onView: View) {
        val titleTextView = onView.findViewById<EmojiAppCompatTextView>(R.id.titleTextView)
        val contentTextView = onView.findViewById<EmojiAppCompatTextView>(R.id.contentTextView)
        val cardView = onView.findViewById<MaterialCardView>(R.id.cardView)

        titleTextView.text = model?.title
        contentTextView.text = model?.subtitle

        model?.paletteColor?.let {
            cardView.setCardBackgroundColor(
                PaletteColors.valueOf(it).hexColor.toColor(onView.context)
            )
        }

        cardView.setOnClickListener { listener?.onItemClick(model) }
    }
}
