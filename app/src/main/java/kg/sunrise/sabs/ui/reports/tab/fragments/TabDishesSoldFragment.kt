package kg.sunrise.sabs.ui.reports.tab.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.*
import kg.sunrise.sabs.ui.reports.tab.activity.DishesSoldActivity
import kg.sunrise.sabs.ui.reports.tab.adapter.DishesSoldOfDateAdapter
import kg.sunrise.sabs.ui.reports.tab.adapter.DishesSoldOfDayAdapter
import kg.sunrise.sabs.utils.*
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.activity_dishes_sold.*
import kotlinx.android.synthetic.main.fragment_dishes_sold.*
import kotlinx.android.synthetic.main.fragment_dishes_sold.progress
import kotlinx.android.synthetic.main.fragment_dishes_sold.recycler_dishes_sold
import kotlinx.android.synthetic.main.fragment_reports.title_layout
import kotlinx.android.synthetic.main.fragment_s_abolition.*
import org.koin.android.viewmodel.ext.android.viewModel

class TabDishesSoldFragment : Fragment() {
    private lateinit var adapterDishesSoldOfDay: DishesSoldOfDayAdapter
    private lateinit var adapterDishesDate: DishesSoldOfDateAdapter
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private var isUpdate =false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dishes_sold, container, false)
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
            }
        }
    }
    fun dateFunc(mStartDate: String, mEndDate: String, mSpinner: String) {
        if( this.isAdded){
            if (mSpinner == "Сегодня"){
                requestDay(mEndDate)
            }else{
                requestDate(mStartDate, mEndDate)
                isUpdate=false
            }
        }else{
            isUpdate= true
            startDate = mStartDate
            endDate = mEndDate
        }
    }
    private fun requestDate(SD: String, ED: String) {
        title_layout.visibility = View.GONE
        dishesDateConfigureViews()
        mainViewModel.getDishesSoldDate(SD, ED)
        dishesDateConfigureViewModel()
    }
    private fun dishesDateConfigureViewModel() {
        mainViewModel.dishesSoldDateResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            onDishesDateReceived(it)
        })
    }
    private fun dishesDateConfigureViews() {
        adapterDishesDate = DishesSoldOfDateAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recycler_dishes_sold.layoutManager = layoutManager
        recycler_dishes_sold.adapter = adapterDishesDate
        adapterDishesDate.setClickListener(object : DishesSoldOfDateAdapter.OnClickListener {
            override fun onItemPositionClick(title: DishesSoldDateData, position: Int) {
                startActivity(
                    Intent(requireActivity(), DishesSoldActivity::class.java)
                        .putExtra(CATEGORY_ID, title.soldDishesDate)
                )
            }
        })
    }
    private fun onDishesDateReceived(dishesSoldDate: DishesSoldDateModel) {
        dishesSoldDate.let {
            adapterDishesDate.swapData(it.data)
            progress.visibility = View.GONE
        }
    }
    private fun requestDay(date : String){
        title_layout.visibility = View.VISIBLE
        progress.visibility = View.VISIBLE
        mainViewModel.getDishesSoldDay(date)
        dishesSoldOfDayConfigureViewModel()
        dishesSoldOfDayConfigureViews()
    }
    private fun dishesSoldOfDayConfigureViewModel() {
        mainViewModel.dishesSoldDayResponseLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
            onDishesSoldOfDayReceived(it)
        })
    }
    private fun dishesSoldOfDayConfigureViews() {
        adapterDishesSoldOfDay = DishesSoldOfDayAdapter()
        val layoutManager = LinearLayoutManager(requireActivity())
        recycler_dishes_sold.layoutManager = layoutManager
        recycler_dishes_sold.adapter = adapterDishesSoldOfDay
    }
    private fun onDishesSoldOfDayReceived(dishesSoldOfDayModel: DishesSoldOfDayModel) {
        dishesSoldOfDayModel.let {
            adapterDishesSoldOfDay.swapData(it.data)
            progress.visibility = View.GONE
        }
    }
}
