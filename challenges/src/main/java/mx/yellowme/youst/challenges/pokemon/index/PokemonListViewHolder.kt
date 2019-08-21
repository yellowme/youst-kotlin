package mx.yellowme.youst.challenges.pokemon.index

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import com.squareup.picasso.Picasso
import mx.yellowme.youst.challenges.R
import mx.yellowme.youst.core.domain.Pokemon
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.RecyclerViewHolderDecorator

/**
 * Reusable view portion.
 */
class PokemonListViewHolder internal constructor(
    itemView: View,
    private val listener: ItemListener<Pokemon>?
) : RecyclerViewHolderDecorator<Pokemon>(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val firstTypeView: View = itemView.findViewById(R.id.firstTypeView)
    private val secondTypeView: View = itemView.findViewById(R.id.secondTypeView)

    private val context: Context = itemView.context

    override fun decorate(model: Pokemon) {
        itemView.setOnClickListener { listener?.onItemClick(model) }

        with(model) {
            imageUrl?.let {
                if (it.isNotEmpty()) {
                    Picasso.get().load(it).into(imageView)
                }
            }

            nameTextView.text = name

            val types = types
            val firstType = types?.getOrNull(0)
            val secondType = types?.getOrNull(1)

            println("For $name - $firstType - $secondType")

            if (firstType != null) {
                DrawableCompat.setTint(
                    firstTypeView.background,
                    firstType.getColorResource(context)
                )
            }

            if (secondType != null) {
                DrawableCompat.setTint(
                    secondTypeView.background,
                    secondType.getColorResource(context)
                )
            }
        }
    }

}
