package kg.sunrise.sabs.ui.reports.tab.fragments
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.*
import kg.sunrise.sabs.ui.reports.tab.adapter.BillsAdapter
import kg.sunrise.sabs.utils.*
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.fragment_reports.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class TabLeftoversFragment : Fragment() {
    private lateinit var adapterBills: BillsAdapter
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private var s = false
    private var isUpdate =false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapsing.visibility = View.GONE
        Glide.with(requireActivity()).load(R.drawable.swipe_up).into(swipe)
        swipe.setOnClickListener(View.OnClickListener {
            if(!s){
                collapsing.visibility = View.VISIBLE
                Glide.with(requireActivity()).load(R.drawable.swipe_down).into(swipe)
                s = true
            }else{
                collapsing.visibility = View.GONE
                Glide.with(requireActivity()).load(R.drawable.swipe_up).into(swipe)
                s = false
            }
        })
        if( this.isAdded){
            isUpdate=false
            Log.e("4", "3")
            Log.e("сох", "$startDate $endDate")
            request(startDate, endDate)
        }
    }
    fun dateFunc(mStartDate:String, mEndDate:String, spinner: String){
        if(this.isAdded){
            request(mStartDate, mEndDate)
            isUpdate=false
            Log.e("4", "1")
            Log.e("сох", "$mStartDate $mEndDate")
        }
        else{
            isUpdate= true
            startDate = mStartDate
            endDate = mEndDate
            Log.e("4", "2")
            Log.e("сох", "$startDate $endDate")
        }
    }
    private fun request(SD:String, ED:String){
        reportConfigureViews()
        mainViewModel.getPayments(SD, ED)
        reportConfigureViewModel()

    }
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    private fun reportConfigureViewModel() {
        mainViewModel.billsResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            click(it)
            onReportReceived(it)
            Log.e("то что нужно вывести", ""+ it)
        })
    }
    private fun reportConfigureViews() {
        adapterBills = BillsAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recycler_report.layoutManager = layoutManager
        recycler_report.adapter= adapterBills
    }
    private fun onReportReceived(report: BillsModel) {
        report.let {
            adapterBills.swapData(it.data)
        }
    }
    private fun onReportReceivedND(report: BillsModel) {
        report.let {
            adapterBills.swapData(it.data)
            Collections.sort(it.data, SortNameU())
        }
    }
    private fun onReportReceivedNU(report: BillsModel) {
        report.let {
            adapterBills.swapData(it.data)
            Collections.sort(it.data, SortNameD())

        }
    } private fun onReportReceivedQD(report: BillsModel) {
        report.let {
            adapterBills.swapData(it.data)
            Collections.sort(it.data, SortQuantityU())

        }
    } private fun onReportReceivedQU(report: BillsModel) {
        report.let {
            adapterBills.swapData(it.data)
            Collections.sort(it.data, SortQuantityD())

        }
    }
    class SortQuantityU : Comparator<BillsData?> {
        override fun compare(o1: BillsData?, o2: BillsData?): Int {
            return o1!!.iteM_PRICE.compareTo(o2!!.iteM_PRICE)
        }
    }
    class SortQuantityD : Comparator<BillsData?> {
        override fun compare(o1: BillsData?, o2: BillsData?): Int {
            return o2!!.iteM_PRICE.compareTo(o1!!.iteM_PRICE)
        }
    }
    class SortNameU : Comparator<BillsData?> {
        override fun compare(o1: BillsData?, o2: BillsData?): Int {
            return o1!!.username.compareTo(o2!!.username)
        }
    }
    class SortNameD : Comparator<BillsData?> {
        override fun compare(o1: BillsData?, o2: BillsData?): Int {
            return o2!!.username.compareTo(o1!!.username)
        }
    }



    private fun click(report: BillsModel){
        sort_name.setOnClickListener(View.OnClickListener {
            if(!a){
                snd(report)
            }else{
                snu(report)
            }
        })
        im_sort_name.setOnClickListener(View.OnClickListener {
            if(!a){
                snd(report)
            }else{
                snu(report)
            }
        })
        sort_quantity.setOnClickListener(View.OnClickListener {
            if(!b){
                sqd(report)
            }else{
                squ(report)
            }
        })
        im_sort_quantity.setOnClickListener(View.OnClickListener {
            if(!b){
                sqd(report)
            }else{
                squ(report)
            }
        })
    }
    private  fun snd(report: BillsModel){
        Glide.with(this).load(R.drawable.ic_arrou_down).into(im_sort_name)
        a = true
        onReportReceivedND(report)
    }
    private  fun snu(report: BillsModel){
        Glide.with(this).load(R.drawable.ic_arrou_ap).into(im_sort_name)
        a = false
        onReportReceivedNU(report)
    }
    private  fun sqd(report: BillsModel){
        Glide.with(this).load(R.drawable.ic_arrou_down).into(im_sort_quantity)
        b = true
        onReportReceivedQD(report)
    }
    private  fun squ(report: BillsModel){
        Glide.with(this).load(R.drawable.ic_arrou_ap).into(im_sort_quantity)
        b = false
        onReportReceivedQU(report)
    }
}
