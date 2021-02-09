package kg.sunrise.sabs.ui.reports.tab.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.CancelsModel
import kg.sunrise.sabs.ui.reports.tab.adapter.CancelsDishesOfDayAdapter
import kg.sunrise.sabs.utils.CATEGORY_ID
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.activity_s_abolition.*
import kotlinx.android.synthetic.main.activity_s_abolition.recycler_cancels
import org.koin.android.viewmodel.ext.android.viewModel

class CanselsActivity : AppCompatActivity() {
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private lateinit var adapterCancelsDishesOfDay: CancelsDishesOfDayAdapter
    var date : String = ""
    private var d: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_abolition)
        back.setOnClickListener(View.OnClickListener {
            finish() })
        d = intent.getStringExtra(CATEGORY_ID)
        date = mainViewModel.normal(d!!)
        requestDay(date)

    }
    private fun requestDay(date : String){
        mainViewModel.getCancelsDay(date)
        cancelsDayConfigureViewModel()
        cancelsDayConfigureViews()
    }
    private fun cancelsDayConfigureViewModel() {
        mainViewModel.cancelsDayResponseLiveData.observe(this, androidx.lifecycle.Observer {
            onCancelsDayReceived(it)
        })
    }
    private fun cancelsDayConfigureViews() {
        adapterCancelsDishesOfDay = CancelsDishesOfDayAdapter()
        val layoutManager = LinearLayoutManager(this)
        recycler_cancels.layoutManager = layoutManager
        recycler_cancels.adapter = adapterCancelsDishesOfDay
    }
    private fun onCancelsDayReceived(cansels: CancelsModel) {
        cansels.let {
            adapterCancelsDishesOfDay.swapData(it.data)
//            Collections.sort(it.data, SortName())
        }
    }
}