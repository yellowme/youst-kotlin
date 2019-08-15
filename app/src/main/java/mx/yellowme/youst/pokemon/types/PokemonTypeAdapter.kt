package mx.yellowme.youst.pokemon.types

import android.view.LayoutInflater
import android.view.ViewGroup
import mx.yellowme.youst.R
import mx.yellowme.youst.core.domain.PokemonTypeSelection
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.recycler.SimpleRecyclerAdapter

/**
 * TODO: Add docs
 */
class PokemonTypeAdapter(
    pokemonTypes: MutableList<PokemonTypeSelection>,
    itemListener: ItemListener<PokemonTypeSelection>?
) : SimpleRecyclerAdapter<PokemonTypeSelection, PokemonTypeListViewHolder>(
    pokemonTypes,
    itemListener
) {

    /**
     * TODO: Add docs for helper method
     */
    private val isWithinLimits: Boolean
        get() {
            var counter = 0
            for (selectionType in mItemList) {
                if (selectionType.isChecked) counter++
            }
            return counter < MAX_SELECTION
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_item_pokemon_type, parent, false)

        return PokemonTypeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonTypeListViewHolder, position: Int) {
        val item = getItem(position) ?: return

        holder.decorate(item)
        with(holder.typeCheckBox) {
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked && !isWithinLimits) {
                    item.isChecked = false
                    this.isChecked = false
                    //TODO: Display message to the user.
                } else {
                    item.isChecked = isChecked
                    this.isChecked = isChecked
                }
            }
        }
    }

    companion object {
        private const val MAX_SELECTION = 5
    }

}
