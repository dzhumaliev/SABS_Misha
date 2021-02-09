package kg.sunrise.sabs.ui.reports.tab.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.DishesSoldOfDayModel
import kg.sunrise.sabs.ui.reports.tab.adapter.DishesSoldOfDayAdapter
import kg.sunrise.sabs.utils.CATEGORY_ID
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.activity_dishes_sold_day.*
import kotlinx.android.synthetic.main.activity_s_abolition.back
import org.koin.android.viewmodel.ext.android.viewModel

class DishesSoldActivity : AppCompatActivity() {
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    private lateinit var adapterDishesSoldOfDay: DishesSoldOfDayAdapter
    var date : String = ""
    private var d: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_sold_day)
        back.setOnClickListener(View.OnClickListener {
            finish() })
        d = intent.getStringExtra(CATEGORY_ID)
        date = mainViewModel.normal(d!!)
        requestDay(date)

    }
    private fun requestDay(date : String){
        mainViewModel.getDishesSoldDay(date)
        dishesSoldOfDayConfigureViewModel()
        dishesSoldOfDayConfigureViews()
    }
    private fun dishesSoldOfDayConfigureViewModel() {
        mainViewModel.dishesSoldDayResponseLiveData.observe(this, androidx.lifecycle.Observer {
            onDishesSoldOfDayReceived(it)
        })
    }
    private fun dishesSoldOfDayConfigureViews() {
        adapterDishesSoldOfDay = DishesSoldOfDayAdapter()
        val layoutManager = LinearLayoutManager(this)
        recycler_dishes_sold_day.layoutManager = layoutManager
        recycler_dishes_sold_day.adapter = adapterDishesSoldOfDay

    }
    private fun onDishesSoldOfDayReceived(dishesSoldOfDayModel: DishesSoldOfDayModel) {
        dishesSoldOfDayModel.let {
            adapterDishesSoldOfDay.swapData(it.data)
        }
    }
}