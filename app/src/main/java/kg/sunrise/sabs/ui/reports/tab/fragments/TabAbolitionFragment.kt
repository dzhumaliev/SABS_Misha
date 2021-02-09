package kg.sunrise.sabs.ui.reports.tab.fragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.CancelDishesOfDayModel
import kg.sunrise.sabs.data.model.CancelsDishesOfDayData
import kg.sunrise.sabs.data.model.CancelsModel
import kg.sunrise.sabs.ui.reports.tab.activity.CanselsActivity
import kg.sunrise.sabs.ui.reports.tab.adapter.CancelDishesOfDateAdapter
import kg.sunrise.sabs.ui.reports.tab.adapter.CancelsDishesOfDayAdapter
import kg.sunrise.sabs.utils.CATEGORY_ID
import kg.sunrise.sabs.utils.endDate
import kg.sunrise.sabs.utils.spinner
import kg.sunrise.sabs.utils.startDate
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.fragment_cb_statistics.*
import kotlinx.android.synthetic.main.fragment_s_abolition.*
import kotlinx.android.synthetic.main.fragment_s_abolition.progress
import kotlinx.android.synthetic.main.fragment_s_abolition.title_layout
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

    class TabAbolitionFragment : Fragment() {
        private val colors = ArrayList<Int>()
        private lateinit var adapterCancelsDishesOfDay: CancelsDishesOfDayAdapter
        private lateinit var adapterCancelsDishesOfDate: CancelDishesOfDateAdapter
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private var isUpdate =false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_s_abolition, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title_layout.visibility = View.GONE
        progress.visibility = View.GONE
        if(isUpdate){
            if (spinner == "Сегодня"){
                requestDay(endDate)
            }else{
                requestDate(startDate, endDate)
                isUpdate=false
                Log.e("1", "3")
                Log.e("сох", "$startDate $endDate")
            }
        }
    }
    fun dateFunc(mStartDate:String, mEndDate:String, mSpinner: String){
        if( this.isAdded){
            if (mSpinner == "Сегодня"){
                requestDay(mEndDate)
            }else{
                requestDate(mStartDate, mEndDate)
                isUpdate=false
                Log.e("1", "1")
                Log.e("сох", "$mStartDate $mEndDate")
            }
        }else{
            isUpdate= true
            startDate = mStartDate
            endDate = mEndDate
            spinner = mSpinner
            Log.e("1", "2")
            Log.e("сох", "$startDate $endDate")
        }
    }
    private fun requestDay(SD:String){
//        progress.visibility = View.VISIBLE
        title_layout.visibility = View.VISIBLE
        mainViewModel.getCancelsDay(SD)
        cancelsDayConfigureViewModel()
        cancelsDayConfigureViews()
    }
    private fun requestDate(SD:String, ED:String){
//        progress.visibility = View.VISIBLE
        title_layout.visibility = View.GONE
        mainViewModel.getCancelsDate(SD, ED)
        cancelsDateConfigureViewModel()
        cancelsDateConfigureViews()
    }
    private fun cancelsDateConfigureViewModel() {
        mainViewModel.cancelsDateResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            onCancelsDateReceived(it)
            sum_receipt_days.text = ""
        })
    }
    private fun cancelsDateConfigureViews() {
        adapterCancelsDishesOfDate = CancelDishesOfDateAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recycler_cancels.layoutManager = layoutManager
        recycler_cancels.adapter = adapterCancelsDishesOfDate
        adapterCancelsDishesOfDate.setClickListener(object : CancelDishesOfDateAdapter.OnClickListener {
            override fun onItemPositionClick(title: CancelsDishesOfDayData, position: Int) {
                startActivity(
                    Intent(requireActivity(), CanselsActivity::class.java)
                        .putExtra(CATEGORY_ID, title.cancelDishesDate)
                )
            }
        })
    }
    private fun onCancelsDateReceived(canselDate: CancelDishesOfDayModel) {
        canselDate.let {
            adapterCancelsDishesOfDate.swapData(it.data)
        }
    }
    private fun cancelsDayConfigureViewModel() {
        mainViewModel.cancelsResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            onCancelsDayReceived(it)
            sum_receipt_days.text = ""
            progress.visibility = View.GONE
        })
    }
    private fun cancelsDayConfigureViews() {
        adapterCancelsDishesOfDay = CancelsDishesOfDayAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recycler_cancels.layoutManager = layoutManager
        recycler_cancels.adapter = adapterCancelsDishesOfDay
    }
    private fun onCancelsDayReceived(cansels: CancelsModel) {
        cansels.let {
            adapterCancelsDishesOfDay.swapData(it.data)
            progress.visibility = View.GONE
        }
    }
}
