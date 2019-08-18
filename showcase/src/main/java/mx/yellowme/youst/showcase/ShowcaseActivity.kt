package mx.yellowme.youst.showcase

import android.os.Bundle
import kotlinx.android.synthetic.main.showcase.*
import mx.yellowme.youst.core.extensions.launch
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.core.hooks.BaseActivity
import mx.yellowme.youst.core.hooks.recycler.ItemListener
import mx.yellowme.youst.core.utils.Activities
import mx.yellowme.youst.core.utils.asJsonArrayOf
import mx.yellowme.youst.core.utils.intentTo
import mx.yellowme.youst.core.utils.readJson
import mx.yellowme.youst.core.R as coreR

class ShowcaseActivity : BaseActivity(), ItemListener<ShowcaseOption> {

    //region Attribute

    override val layoutId = R.layout.showcase

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val showcaseRecycler = ShowcaseRecyclerView.Builder()
            .with(showcaseOptions)
            .into(this)

        classLoader?.readJson("showcase.json")?.asJsonArrayOf<ShowcaseOption>()?.let {
            showcaseRecycler.setData(it)
        } ?: throw RuntimeException("Reading corrupted showcase JSON file")
    }

    override fun onItemClick(item: ShowcaseOption?) {
        when (item!!.optionId) {
            ShowcaseOptionId.CHALLENGES -> {
                launch(intentTo(Activities.Challenges))
            }
            ShowcaseOptionId.PLAYGROUND -> {
                toast("TODO: Must migrate current navigation component")
            }
            ShowcaseOptionId.MINI_APPS,
            ShowcaseOptionId.PORTFOLIO -> {
                toast(getString(coreR.string.work_in_progress))
            }
        }
    }
}