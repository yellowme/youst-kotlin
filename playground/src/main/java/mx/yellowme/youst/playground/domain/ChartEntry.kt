package mx.yellowme.youst.playground.domain

import com.github.mikephil.charting.data.Entry

/**
 * Created by adrianleyvasanchez on 04,October,2019
 */
class ChartEntry(
    x: Float,
    y: Float,
    val z: Float? = 0f
) : Entry(x, y)
