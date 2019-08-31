package mx.yellowme.youst.miniapps.ui.chords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.chords.*
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.hooks.adapter.CardAdapter
import mx.yellowme.youst.core.templates.showcase.ShadowTransformer
import mx.yellowme.youst.core.utils.dipToPx
import mx.yellowme.youst.miniapps.R
import mx.yellowme.youst.miniapps.domain.Chord

class ChordsActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    private var adapter: ChordsAdapter? = null
    private var shadowTransformer: ShadowTransformer? = null

    override val layoutId = R.layout.chords

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ChordsAdapter(null)
        adapter!!.addItems(Chord.all())

        updateSelected(Chord.all().first())

        chordsViewPager?.pageMargin = dipToPx(12)
        setupViewPager()
    }

    private fun setupViewPager() {
        adapter?.let {
            shadowTransformer = ShadowTransformer(
                chordsViewPager,
                it
            ).apply {
                enableScaling(true)
            }
            chordsViewPager?.adapter = it
            chordsViewPager?.setPageTransformer(false, shadowTransformer)
            chordsViewPager?.offscreenPageLimit = 1

            chordsViewPager?.addOnPageChangeListener(this)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        /*NO-OP*/
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        /*NO-OP*/
    }

    override fun onPageSelected(position: Int) {
        updateSelected(adapter!!.getItemAt(position)!!)
    }

    private fun updateSelected(chord: Chord) {
        selectedChordTextView.text = Chord.transform(chord).name
    }
}

//TODO: Generify
class ChordsAdapter(itemListener: ItemListener<Chord>?) : PagerAdapter(),
    CardAdapter {

    private val mViews: MutableList<MaterialCardView?>
    private val mData: MutableList<Chord?>

    private var mItemListener: ItemListener<Chord>?

    override var baseElevation: Float = 0f

    init {
        mData = ArrayList()
        mViews = ArrayList()
        mItemListener = itemListener
    }

    fun addCardItem(item: Chord) {
        mViews.add(null)
        mData.add(item)
    }

    fun addItems(items: List<Chord>) {
        for (item in items) {
            addCardItem(item)
        }
    }

    fun getItemAt(position: Int): Chord? {
        return mData[position]
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
            .inflate(R.layout.chords_card, container, false)

        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById<MaterialCardView>(R.id.cardView)

        if (baseElevation == 0f) {
            baseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = baseElevation * CardAdapter.MAX_ELEVATION_FACTOR
        mViews[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews[position] = null
    }

    private fun bind(item: Chord?, view: View) {
        val labelTextView = view.findViewById<TextView>(R.id.labelTextView)

        labelTextView.text = item?.name
    }

}
