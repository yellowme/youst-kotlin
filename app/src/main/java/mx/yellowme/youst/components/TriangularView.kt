package mx.yellowme.youst.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import mx.yellowme.youst.R
import mx.yellowme.youst.extensions.consumeTypeArray


/**
 * youst-kotlin
 * Created by Mario Guillermo on 2019-07-17.
 */
class TriangularView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var arcPaint: Paint
    private var path: Path

    @ColorInt
    var triangleColor = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }

    init {
        attrs?.consumeTypeArray(context, R.styleable.TriangularView) {
            triangleColor = getInt(R.styleable.TriangularView_triangleColor, 0)
        }
        path = Path()
        arcPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { drawTriangle(it) }
    }

    private fun drawTriangle(canvas: Canvas) {
        setBackgroundColor(Color.TRANSPARENT)
        val canvasWidth = canvas.width.toFloat()
        val canvasHeight = canvas.height.toFloat()
        arcPaint.run {
            strokeWidth = 0f
            pathEffect = null
            color = triangleColor
            style = Paint.Style.FILL_AND_STROKE
        }
        path.run {
            moveTo(0f, canvasHeight)
            lineTo(canvasWidth, canvasHeight)
            lineTo(canvasWidth, 0f)
            lineTo(0f, canvasHeight)
            close()
        }
        canvas.drawPath(path, arcPaint)
    }
}
