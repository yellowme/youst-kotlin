package mx.yellowme.youst.playground.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.component_chart_selector.view.*
import mx.yellowme.youst.core.extensions.consumeTypeArray
import mx.yellowme.youst.core.extensions.inflate
import mx.yellowme.youst.core.utils.loadJsonObject
import mx.yellowme.youst.playground.R
import mx.yellowme.youst.playground.domain.ChartSetting
import mx.yellowme.youst.playground.domain.ChartType
import mx.yellowme.youst.playground.ui.chart.common.OnChangeListener
import mx.yellowme.youst.core.R as coreR

/**
 * @author adrianleyvasanchez
 * @since 30,September,2019
 */
class ChartSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var listener: OnChangeListener? = null

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

    fun setup(activity: AppCompatActivity, listener: OnChangeListener) {
        this.listener = listener
        activity.loadJsonObject<ChartSetting>("chart_settings.json")?.let {
            when(ChartType.valueOf(it.type)) {
                ChartType.BAR -> firstOption.isChecked = true
                ChartType.LINE -> secondOption.isChecked = true
                ChartType.BUBBLE -> thirdOption.isChecked = true
            }
        }
    }

    init {
        inflate(R.layout.component_chart_selector, context)
        firstOption.text = ChartType.BAR.name
        secondOption.text = ChartType.LINE.name
        thirdOption.text = ChartType.BUBBLE.name
        attrs?.consumeTypeArray(context, R.styleable.ChartSelector) {
            backgroundColorRes = getResourceId(R.styleable.ChartSelector_selectorBackgroundColor, coreR.color.white)
            optionsTextColorRes = getResourceId(R.styleable.ChartSelector_selectorOptionsTextColor, coreR.color.guitar_black)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                firstOption.id -> {
                    listener?.onChangeChart(ChartType.valueOf(firstOption.text.toString()))
                }
                secondOption.id -> {
                    listener?.onChangeChart(ChartType.valueOf(secondOption.text.toString()))
                }
                thirdOption.id -> {
                    listener?.onChangeChart(ChartType.valueOf(thirdOption.text.toString()))
                }
            }
        }
    }

}