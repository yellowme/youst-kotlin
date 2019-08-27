package mx.yellowme.youst.core.hooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.card.MaterialCardView
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.hooks.adapter.CardAdapter.Companion.MAX_ELEVATION_FACTOR
import mx.yellowme.youst.core.hooks.recycler.ItemListener

interface CardAdapter {

    val baseElevation: Float

    fun getCardViewAt(position: Int): CardView?

    companion object {
        const val MAX_ELEVATION_FACTOR = 3
    }

    fun getCount(): Int

}

@Suppress("SpellCheckingInspection")
abstract class Decorator<Model>{
    var listener: ItemListener<Model>? = null

    abstract fun decorate(model: Model?, onView: View)

    fun bindListener(listener: ItemListener<Model>?) {
        this.listener = listener
    }
}

/**
 * TODO: Needs refactor
 */
@Suppress("MemberVisibilityCanBePrivate")
open class CardPagerAdapter<Model>(
    val decorator: Decorator<Model>
) : PagerAdapter(), CardAdapter {

    private val views: MutableList<MaterialCardView?>
    private val data: MutableList<Model?>

    override var baseElevation: Float = 0f

    init {
        data = arrayListOf()
        views = arrayListOf()
    }

    fun addCardItem(item: Model) {
        views.add(null)
        data.add(item)
    }

    fun addItems(items: List<Model>) {
        for (item in items) {
            addCardItem(item)
        }
    }

    override fun getCardViewAt(position: Int): CardView? {
        return views[position]
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater
            .from(container.context)
            .inflate(R.layout.template_generic_showcase_card, container, false)

        container.addView(view)
        bind(data[position], view)
        val cardView = view.findViewById<MaterialCardView>(R.id.cardView)

        if (baseElevation == 0f) {
            baseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = baseElevation * MAX_ELEVATION_FACTOR
        views[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        views[position] = null
    }

    private fun bind(item: Model?, view: View) {
        decorator.decorate(item, view)
    }

}
