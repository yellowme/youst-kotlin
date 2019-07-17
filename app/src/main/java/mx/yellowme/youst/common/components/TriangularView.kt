package mx.yellowme.youst.common.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import mx.yellowme.youst.R


/**
 * youst-kotlin
 * Created by Mario Guillermo on 2019-07-17.
 */
class TriangularView : View {

    private lateinit var arcPaint: Paint
    private lateinit var path1: Path
    private lateinit var path2: Path
    @ColorInt
    var triangleColor = Color.BLACK

    constructor(context: Context) : super(context) {
        setup(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        val typedArray: TypedArray? = context.obtainStyledAttributes(attrs, R.styleable.TriangularView)
        typedArray?.getInt(R.styleable.TriangularView_triangleColor, 0)?.let { triangleColor = it }
        typedArray?.recycle()
        path1 = Path()
        path2 = Path()
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
        path1.run {
            moveTo(0f, canvasHeight)
            lineTo(canvasWidth, canvasHeight)
            lineTo(canvasWidth, 0f)
            lineTo(0f, canvasHeight)
            close()
        }
        canvas.drawPath(path1, arcPaint)
    }

    fun changeTriangleColor(@ColorInt color: Int) {
        triangleColor = color
        invalidate()
    }
}
