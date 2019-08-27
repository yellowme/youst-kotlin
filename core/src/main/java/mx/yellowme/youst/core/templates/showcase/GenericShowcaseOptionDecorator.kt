package mx.yellowme.youst.core.templates.showcase

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.domain.GenericShowcasedOption
import mx.yellowme.youst.core.hooks.adapter.Decorator

class GenericShowcaseOptionDecorator<Model : GenericShowcasedOption> : Decorator<Model>() {
    override fun decorate(model: Model?, onView: View) {
        val titleTextView = onView.findViewById<TextView>(R.id.titleTextView)
        val contentTextView = onView.findViewById<TextView>(R.id.contentTextView)
        val cardView = onView.findViewById<MaterialCardView>(R.id.cardView)

        titleTextView.text = model?.title
        contentTextView.text = model?.subtitle

        model?.hexColor?.let {
            cardView.setCardBackgroundColor(Color.parseColor(it))
        }

        cardView.setOnClickListener { listener?.onItemClick(model) }
    }
}
