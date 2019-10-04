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
 * @since 30,September,2019
 */
interface ChartSelectorActionListener {
    fun onChangeAction(checkedType: ChartType)
}

class ChartSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var actionListener: ChartSelectorActionListener? = null

    var firstOptionText: String? = null
        set(value) {
            field = value
            firstOption.text = firstOptionText
        }

    var secondOptionText: String? = null
        set(value) {
            field = value
            secondOption.text = secondOptionText
        }

    var thirdOptionText: String? = null
        set(value) {
            field = value
            thirdOption.text = thirdOptionText
        }

    private var backgroundColorRes: Int = -1
        set(value) {
            field = value
            if (backgroundColorRes != -1) {
                mainContainer.setBackgroundColor(
                    ContextCompat.getColor(context, backgroundColorRes)
                )
            }
        }

    private var optionsTextColorRes: Int = -1
        set(value) {
            field = value
            if (optionsTextColorRes != -1) {
                ContextCompat.getColor(context, optionsTextColorRes).run {
                    firstOption.setTextColor(this)
                    secondOption.setTextColor(this)
                    thirdOption.setTextColor(this)
                }
            }
        }

    init {
        inflate(R.layout.component_chart_selector, context)
        attrs?.consumeTypeArray(context, R.styleable.ChartSelector) {
            backgroundColorRes = getResourceId(R.styleable.ChartSelector_selectorBackgroundColor, coreR.color.white)
            optionsTextColorRes = getResourceId(R.styleable.ChartSelector_selectorOptionsTextColor, coreR.color.guitar_black)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                firstOption.id -> {
                    actionListener?.onChangeAction(
                        ChartType.valueOf(firstOptionText ?: "")
                    )
                }
                secondOption.id -> {
                    actionListener?.onChangeAction(
                        ChartType.valueOf(secondOptionText ?: "")
                    )

                }
                thirdOption.id -> {
                    actionListener?.onChangeAction(
                        ChartType.valueOf(thirdOptionText ?: "")
                    )
                }
            }
        }
    }

}