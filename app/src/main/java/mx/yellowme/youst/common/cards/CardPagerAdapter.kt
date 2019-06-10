package mx.yellowme.youst.common.cards;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import mx.yellowme.youst.R
import mx.yellowme.youst.common.ItemListener
import mx.yellowme.youst.common.cards.CardAdapter.Companion.MAX_ELEVATION_FACTOR
import mx.yellowme.youst.domain.Challenge
import java.util.*

/**
 * TODO: Needs refactor
 */
open class CardPagerAdapter(itemListener: ItemListener<Challenge>) : PagerAdapter(), CardAdapter {

    private val mViews: MutableList<MaterialCardView?>
    private val mData: MutableList<Challenge?>

    private var mItemListener: ItemListener<Challenge>

    override var baseElevation: Float = 0f

    init {
        mData = ArrayList()
        mViews = ArrayList()
        mItemListener = itemListener
    }

    fun addCardItem(item: Challenge) {
        mViews.add(null)
        mData.add(item)
    }

    fun addItems(items: List<Challenge>) {
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
            .inflate(R.layout.card_item_challenge, container, false)

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

    private fun bind(item: Challenge?, view: View) {
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val contentTextView = view.findViewById<TextView>(R.id.contentTextView)
        val mainAction = view.findViewById<MaterialButton>(R.id.mainAction)

        titleTextView.text = item?.title
        contentTextView.text = item?.description

        val imageView = view.findViewById<ImageView>(R.id.bookmark)
        item?.type?.resourceId?.let { imageView.setBackgroundResource(it) }

        mainAction.setOnClickListener { mItemListener.onItemClick(item) }
    }

}

