package mx.yellowme.youst.core.templates

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.template_generic_showcase.*
import mx.yellowme.youst.core.R
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.templates.CardAdapter.Companion.MAX_ELEVATION_FACTOR
import mx.yellowme.youst.core.utils.dipToPx
import mx.yellowme.youst.core.utils.loadJsonArray
import java.util.*

data class GenericShowcasedOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val hexColor: String
)

abstract class GenericShowcaseActivity : BaseActivity(), ItemListener<GenericShowcasedOption> {

    private var mCardAdapter: CardPagerAdapter? = null
    private var mCardShadowTransformer: ShadowTransformer? = null

    override val layoutId = R.layout.template_generic_showcase

    abstract val titleResId: Int

    abstract val subtitleResId: Int

    abstract val fileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCardAdapter = CardPagerAdapter(this)

        titleTextView.text = getString(titleResId)
        subtitleTextView.text = getString(subtitleResId)

        loadJsonArray<GenericShowcasedOption>(fileName)?.let {
            mCardAdapter?.addItems(it)
        } ?: throw RuntimeException("Reading corrupted dashboard JSON file")

        showcaseItemsViewPager?.pageMargin = dipToPx(12)
        setupViewPager()
    }

    private fun setupViewPager() {
        mCardAdapter?.let {
            mCardShadowTransformer = ShadowTransformer(
                showcaseItemsViewPager,
                it
            ).apply {
                enableScaling(true)
            }
            showcaseItemsViewPager?.adapter = it
            showcaseItemsViewPager?.setPageTransformer(false, mCardShadowTransformer)
            showcaseItemsViewPager?.offscreenPageLimit = 3
        }
    }
}

interface CardAdapter {

    val baseElevation: Float

    fun getCardViewAt(position: Int): CardView?

    companion object {
        const val MAX_ELEVATION_FACTOR = 4
    }

    fun getCount(): Int

}

/**
 * TODO: Needs refactor
 */
@Suppress("MemberVisibilityCanBePrivate")
open class CardPagerAdapter(itemListener: ItemListener<GenericShowcasedOption>) : PagerAdapter(),
    CardAdapter {

    private val mViews: MutableList<MaterialCardView?>
    private val mData: MutableList<GenericShowcasedOption?>

    private var mItemListener: ItemListener<GenericShowcasedOption>

    override var baseElevation: Float = 0f

    init {
        mData = ArrayList()
        mViews = ArrayList()
        mItemListener = itemListener
    }

    fun addCardItem(item: GenericShowcasedOption) {
        mViews.add(null)
        mData.add(item)
    }

    fun addItems(items: List<GenericShowcasedOption>) {
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

    private fun bind(item: GenericShowcasedOption?, view: View) {
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
