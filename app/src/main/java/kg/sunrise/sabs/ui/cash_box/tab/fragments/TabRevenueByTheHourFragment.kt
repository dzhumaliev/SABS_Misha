package kg.sunrise.sabs.ui.cash_box.tab.fragments
import android.graphics.Color
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.CashBoxByTimeModel
import kg.sunrise.sabs.ui.cash_box.tab.fragments.custom.MyValueFormatter
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.fragment_cb_rbth.*
import org.koin.android.viewmodel.ext.android.viewModel

open class TabRevenueByTheHourFragment : Fragment() {
    val onValueSelectedRectF = RectF()
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private val entries1 = ArrayList<BarEntry>()
    private var set1 = BarDataSet(entries1, "Bar 1")
    private val group: ArrayList<BarEntry> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cb_rbth, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    fun dateFunc(startDate:String, endDate:String){
//        val d = mainViewModel.getNextDay(startDate)
        mainViewModel.getCashBoxByTime(startDate, endDate)
        cashBoxConfigureViewModel()
    }
    private fun cashBoxConfigureViewModel() {
        mainViewModel.cbtResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            onCashBoxByTimeReceived(it)
        })
    }
    private fun onCashBoxByTimeReceived(cbt: CashBoxByTimeModel) {
            for (i in cbt.data) {
                group.add(
//                    BarEntry(i.toFloat(), floatArrayOf(getRandom(1f, 50f), getRandom(1f, 50f))
                    BarEntry(i.hour.toFloat(), i.totalAmount.toFloat())
                    )
            }
        graphics()
    }
    private fun getRandom(range: Float, start: Float): Float {
        return (Math.random() * range).toFloat() + start
    }
    private fun graphics(){
//        val xAxisFormatter: ValueFormatter = AxisValueFormatter(barchart)
        val xAxis: XAxis = barchart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.labelCount = 7
//        xAxis.valueFormatter = xAxisFormatter
        val custom: ValueFormatter = MyValueFormatter("KGS")
        val leftAxis: YAxis = barchart.axisLeft
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f
        val bardataset = BarDataSet(group, "Cells")
        bardataset.barShadowColor = Color.RED
        bardataset.barShadowColor
        bardataset.color = resources.getColor(R.color.colorBlueW)
        bardataset.highLightColor = resources.getColor(R.color.colorBlueB)
        bardataset.highLightColor = resources.getColor(R.color.colorBlueB)
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS
        barchart.animateY(5000)
        val data = BarData(set1, bardataset)
        barchart.data = data
//        barchart.xAxis.textSize = 12f
        barchart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onNothingSelected() {
            }
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e == null) return
                val bounds = onValueSelectedRectF
                barchart.getBarBounds(e as BarEntry?, bounds)
                val position: MPPointF = barchart.getPosition(e, AxisDependency.LEFT)
                Log.e("bounds", bounds.toString())
                Log.e("position", position.toString())
                Log.e("x-index", "low: " + barchart.lowestVisibleX + ", high: " + barchart.highestVisibleX)
                MPPointF.recycleInstance(position)
            }
        })
    }

}








































//        val graph = findViewById(R.id.graph_cash_box) as GraphView
//        Glide.with(this).load(R.drawable.ic_logo).into(image)
//        val series =
////            BarGraphSeries(
////                arrayOf<DataPoint>(
////                    DataPoint(0, -1),
////                    DataPoint(1, 5),
////                    DataPoint(2, 3),
////                    DataPoint(3, 2),
////                    DataPoint(4, 6)
////                )
////            )
////        graph_cash_box.addSeries(series)

//        series1 = LineGraphSeries<DataPoint>()
////        var x:Double = 0.0
////        var y:Double = 0.0
////        val sigma = 2.5
////        val media = 300.0
////
////        for ( i in 0..295){
////            x = i.toDouble()
////            y = exp(-((x-media).pow(2))/(sigma* sqrt(2* PI))) /(sigma* sqrt(2* PI))
////            series1.appendData(DataPoint(x,y),true,40)
////        }
////        graph_cash_box.viewport.setMaxX(350.0)
////        graph_cash_box.addSeries(series1)
////        graph_cash_box.viewport.isXAxisBoundsManual = true
////        graph_cash_box.viewport.isScalable = true
////        graph_cash_box.viewport.isScrollable = true
////    }
