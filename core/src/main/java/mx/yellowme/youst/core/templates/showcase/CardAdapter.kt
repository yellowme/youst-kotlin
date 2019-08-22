package mx.yellowme.youst.core.templates.showcase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.card.MaterialCardView
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.domain.GenericShowcasedOption
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.templates.showcase.CardAdapter.Companion.MAX_ELEVATION_FACTOR

interface CardAdapter {

    val baseElevation: Float

    fun getCardViewAt(position: Int): CardView?

    companion object {
        const val MAX_ELEVATION_FACTOR = 3
    }

    fun getCount(): Int

}

/**
 * TODO: Needs refactor
 */
@Suppress("MemberVisibilityCanBePrivate")
open class CardPagerAdapter<Model : GenericShowcasedOption>(itemListener: ItemListener<Model>) : PagerAdapter(),
    CardAdapter {

    private val mViews: MutableList<MaterialCardView?>
    private val mData: MutableList<Model?>

    private var mItemListener: ItemListener<Model>

    override var baseElevation: Float = 0f

    init {
        mData = ArrayList()
        mViews = ArrayList()
        mItemListener = itemListener
    }

    fun addCardItem(item: Model) {
        mViews.add(null)
        mData.add(item)
    }

    fun addItems(items: List<Model>) {
        for (item in items) {
            addCardItem(item)
        }
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mViews[position]
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater
            .from(container.context)
            .inflate(R.layout.template_generic_showcase_card, container, false)

        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById<MaterialCardView>(R.id.cardView)

        if (baseElevation == 0f) {
            baseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = baseElevation * MAX_ELEVATION_FACTOR
        mViews[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews[position] = null
    }

    private fun bind(item: Model?, view: View) {
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val contentTextView = view.findViewById<TextView>(R.id.contentTextView)
        val cardView = view.findViewById<MaterialCardView>(R.id.cardView)

        titleTextView.text = item?.title
        contentTextView.text = item?.subtitle

        item?.hexColor?.let {
            cardView.setCardBackgroundColor(Color.parseColor(it))
        }

        cardView.setOnClickListener { mItemListener.onItemClick(item) }
    }

}
