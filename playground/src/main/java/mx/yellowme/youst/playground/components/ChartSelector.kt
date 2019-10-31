package mx.yellowme.youst.playground.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.component_chart_selector.view.*
import mx.yellowme.youst.core.extensions.consumeTypeArray
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.core.R as coreR

/**
 * @author adrianleyvasanchez
 * @since 29,September,2019
 */
interface OnChangeListener {
    /**
     * TODO: Add docs
     */
    fun didChangeChartType(type: ChartType)
}

/**
 * @author adrianleyvasanchez
 * @since 30,September,2019
 */
class ChartSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    //region Attributes

    var delegate: OnChangeListener? = null

    var currentTypeSelected: ChartType = ChartType.LINE
        set(value) {
            field = value
            when(currentTypeSelected) {
                ChartType.BAR -> firstOption.isChecked = true
                ChartType.LINE -> secondOption.isChecked = true
                ChartType.BUBBLE -> thirdOption.isChecked = true
            }
        }

    private var backgroundColorRes: Int = -1
        set(value) {
            field = value
            if (backgroundColorRes != -1) {
                radioGroup.setBackgroundColor(
                    ContextCompat.getColor(context, backgroundColorRes)
                )
            }
        }

    private var optionsTextColorRes: Int = -1
        set(value) {
            field = value
            updateOptionsTextColor()
        }

    //endregion

    //region Setup

    init {
        inflate(R.layout.component_chart_selector, context)

        firstOption.text = ChartType.BAR.name
        secondOption.text = ChartType.LINE.name
        thirdOption.text = ChartType.BUBBLE.name

        attrs?.consumeTypeArray(context, R.styleable.ChartSelector) {
            backgroundColorRes = getResourceId(
                R.styleable.ChartSelector_selectorBackgroundColor, coreR.color.white
            )
            optionsTextColorRes = getResourceId(
                R.styleable.ChartSelector_selectorOptionsTextColor, coreR.color.guitar_black
            )
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            listOf(firstOption, secondOption, thirdOption).single {
                it.id == checkedId
            }.run {
                delegate?.didChangeChartType(ChartType.valueOf(text.toString()))
            }
        }
    }

    //endregion

    //region Helpers

    private fun updateOptionsTextColor() {
        if (optionsTextColorRes != -1) {
            ContextCompat.getColor(context, optionsTextColorRes).run {
                firstOption.setTextColor(this)
                secondOption.setTextColor(this)
                thirdOption.setTextColor(this)
            }
        }
    }

    //endregion

}
