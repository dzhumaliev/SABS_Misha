package kg.sunrise.sabs.ui.cash_box.tab.fragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.sunrise.sabs.R
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.fragment_cb_statistics.*
import org.koin.android.viewmodel.ext.android.viewModel

class TabStatisticsFragment : Fragment() {
    private val mainViewModel: SecondApiActivityViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cb_statistics, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress.visibility = View.GONE
    }
    fun dateFunc(startDate:String, endDate:String){
        progress.visibility = View.VISIBLE
        mainViewModel.getCashBoxStatistics(startDate, endDate)
        cashBoxConfigureViewModel()

    }
    private fun cashBoxConfigureViewModel() {
        mainViewModel.cbsResponseLiveData.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            total_rbth.text = it.data.amountOfCashbox
            average_amount.text = it.data.averageAmount
            totalAmount.text = it.data.totalAmount
            guestCount.text = it.data.guestCount
            checkCount.text = it.data.checkCount
            openTables.text = it.data.openTables
            closeTables.text = it.data.closeTables
            progress.visibility = View.GONE
        })
    }
}
