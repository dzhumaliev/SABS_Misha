package kg.sunrise.sabs.ui.reports.tab.fragments
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.TopSellingData
import kg.sunrise.sabs.data.model.TopSellingModel
import kg.sunrise.sabs.ui.reports.tab.adapter.TopSellingAdapter
import kg.sunrise.sabs.utils.endDate
import kg.sunrise.sabs.utils.startDate
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.fragment_stat_top_selling.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class TabTopSellingFragment : Fragment() {
    private val colors = ArrayList<Int>()
    private lateinit var adapterTS: TopSellingAdapter
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private var isUpdate =false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stat_top_selling, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if( this.isAdded) {
            request(startDate, endDate)
            isUpdate = false
        }
    }
    private fun chart(ts: ArrayList<TopSellingData>){
        chartPie.setUsePercentValues(true)
        chartPie.description.isEnabled = false
//        chartPie.setExtraOffsets(5f, 10f, 5f, 5f)
        chartPie.setExtraOffsets(0f, 0f, 0f, 0f)
        chartPie.dragDecelerationFrictionCoef = 0.95f
//        chartPie.setCenterTextTypeface(tfLight)
//        chartPie.centerText = generateCenterSpannableText()
        chartPie.isDrawHoleEnabled = true
        chartPie.setHoleColor(Color.WHITE)
        chartPie.setTransparentCircleColor(Color.WHITE)
        chartPie.setTransparentCircleAlpha(110)
        chartPie.holeRadius = 30f
        chartPie.transparentCircleRadius = 0f
        chartPie.setDrawCenterText(true)
        chartPie.rotationAngle = 0f
        chartPie.isRotationEnabled = true
        chartPie.isHighlightPerTapEnabled = true
        val entries = ArrayList<PieEntry>()
        for (i in ts.iterator()){
            entries.add(PieEntry(i.percentCount))
        }
        val dataSet = PieDataSet(entries, "")//текст
//        dataSet.setDrawIcons(false)
//        dataSet.sliceSpace = 3f// пространственный отступ
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f
        // add a lot of colors
        // add a lot of colors
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
//        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors// присвоить к бэкграунду списка
        //dataSet.setSelectionShift(0f);
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chartPie))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
//        data.setValueTypeface(tfLight)
        chartPie.data = data
        chartPie.highlightValues(null)
        chartPie.invalidate()
        chartPie.animateY(1400, Easing.EaseInOutQuad)
    }
    fun dateFunc(mStartDate:String, mEndDate:String){
        if( this.isAdded) {
            request(mStartDate, mEndDate)
            isUpdate=false
            request(startDate, endDate)
        }else{
            isUpdate= true
            startDate = mStartDate
            endDate = mEndDate
            request(startDate, endDate)
        }
    }
    private fun request(SD:String, ED:String){
        topSellingConfigureViews()
        mainViewModel.getTopSelling(SD, ED)
        topSellingConfigureViewModel()
    }
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }
    private fun topSellingConfigureViewModel() {
        mainViewModel.topSellingResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            topSellingReceived(it)
            chart(it.data)
        })
    }
    private fun topSellingConfigureViews() {
        adapterTS = TopSellingAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recycler_chart_pie.layoutManager = layoutManager
        recycler_chart_pie.adapter = adapterTS
    }
    private fun topSellingReceived(ts: TopSellingModel) {
        ts.let {
            adapterTS.swapData(it.data)
        }
    }































//    private fun payments(sD: String, eD: String){
//        mainViewModel.getPayments(sD, eD)
//        paymentsConfigureViewModel()
//        paymentsConfigureViews()
//    }
//    private fun paymentsConfigureViewModel() {
//        mainViewModel.paymentsResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
//            onPaymentsReceived(it)
//            Log.e("то что нужно вывести", ""+ it)
//        })
//    }
//    private fun paymentsConfigureViews() {
//        adapterPayments = PaymentsAdapter()
//        val layoutManager = LinearLayoutManager(activity)
//        recycler_chart_pie.layoutManager = layoutManager
//        recycler_chart_pie.adapter = adapterPayments
//    }
//    private fun onPaymentsReceived(namUyrList: PaymentsModel) {
//        namUyrList.let {
//            adapterPayments.swapData(it.data)
//        }
//    }
//    pie archiv
//        chartPie.isRotationEnabled = true
//        //pieChart.setUsePercentValues(true);
//        chartPie.setHoleColor(Color.WHITE)
//        //pieChart.setCenterTextColor(Color.BLACK);
//        chartPie.holeRadius = 60f
//        chartPie.setTransparentCircleAlpha(0)
//        chartPie.setCenterTextSize(18f)
//        addDataSet()




//
//    pieData.add(SliceValue(15F, Color.BLUE))
//    pieData.add(SliceValue(25F, Color.GRAY))
//    pieData.add(SliceValue(10F, Color.RED))
//    pieData.add(SliceValue(60F, Color.MAGENTA))
//    val pieChartData = PieChartData(pieData)
//    chartPie.pieChartData = pieChartData
//    pieChartData.setHasLabels(true).valueLabelTextSize = 18
////        pieChartData.slicesSpacing = 24// отступы между элементами
//    pieChartData.setHasCenterCircle(true)
////            .setCenterText1("")
////            .setCenterText1FontSize(12)
////            .centerText1Color = R.color.colorBlack
//    chartPie.pieChartData = pieChartData
}
//    private fun addDataSet() {
//        Log.d(TAG, "addDataSet started")
//        val yEntrys: ArrayList<PieEntry> = ArrayList()
//        yEntrys.add(PieEntry(1.5f, "33"))
//        yEntrys.add(PieEntry(3.5f, "66"))
//        yEntrys.add(PieEntry(5.5f, "99"))
//        yEntrys.add(PieEntry(7.5f, "120"))
//        //create the data set
//        val pieDataSet = PieDataSet(yEntrys, "Employee Sales")
//        pieDataSet.sliceSpace = 2f
//        pieDataSet.valueTextSize = 12f
//
//        //add colors to dataset
//        val colors: ArrayList<Int> = ArrayList()
//        colors.add(Color.GRAY)
//        colors.add(Color.BLUE)
//        colors.add(Color.RED)
//        colors.add(Color.GREEN)
//        colors.add(Color.CYAN)
//        colors.add(Color.YELLOW)
//        colors.add(Color.MAGENTA)
//        pieDataSet.colors = colors
//
//        //add legend to chart
//        val legend: Legend = chartPie.legend
//        legend.form = Legend.LegendForm.CIRCLE
//        legend.position = Legend.LegendPosition.LEFT_OF_CHART
//
//        //create pie data object
//        val pieData = PieData(pieDataSet)
//        chartPie.setData(pieData)
//        chartPie.invalidate()
//    }
//}
