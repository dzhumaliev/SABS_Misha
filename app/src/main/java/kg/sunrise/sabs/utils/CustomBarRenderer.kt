package kg.sunrise.sabs.utils

import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class CustomBarRenderer constructor(
    chart: BarChart,
    animator: ChartAnimator,
    vpHandler: ViewPortHandler,
    cornerDimens: Float
) : BarChartRenderer(chart, animator, vpHandler)
//
//chart.renderer = CustomBarRenderer(chart, chart.animator, chart.viewPortHandler, cornersDimens)
//
//init {
//    mRendererPaint = Paint().also {
//        // all paint properties you need
//    }
//}
//override fun drawDataSet(c: Canvas, dataSet: IBarDataSet, index: Int) {
//
//    //....
//    c.drawRoundRect(
//        buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
//        buffer.buffer[j + 3], cornersDimen, cornersDimen, mRenderPaint
//}
