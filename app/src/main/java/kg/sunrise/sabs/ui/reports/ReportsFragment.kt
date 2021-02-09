package kg.sunrise.sabs.ui.reports
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker
import kg.sunrise.sabs.R
import kg.sunrise.sabs.ui.reports.tab.main.ReportsSPAdapterTab
import kg.sunrise.sabs.utils.*
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ReportsFragment : Fragment() {
    private val mainViewModel: SecondApiActivityViewModel by viewModel()
    var sectionsPagerAdapter: ReportsSPAdapterTab? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        Glide.with(this).load(R.drawable.ic_calendar).into(image_calendar_cash_box)
        tt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()).toString()
        day = SimpleDateFormat("dd", Locale.getDefault()).format(Date()).toInt()
        month = SimpleDateFormat("MM", Locale.getDefault()).format(Date()).toInt()
        year = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date()).toInt()
        reportsViewModel = ViewModelProviders.of(this).get(ReportsViewModel::class.java)
        text_data_cash_box.text = tt
        sectionsPagerAdapter = activity?.let {
            ReportsSPAdapterTab(it, childFragmentManager) }
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(requireActivity(), R.array.array_date, R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spinner.adapter = adapter
        if (SpinnerPreference.getAccessToken(requireContext()) == "3"){
            spinner.setSelection(0)
        }else{
            spinner.setSelection(Integer.parseInt(SpinnerPreference.getAccessToken(requireContext())))
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long) {
                val choose = resources.getStringArray(R.array.array_date)
                SpinnerPreference.setAccessToken(requireContext(), selectedItemPosition.toString())
                when (selectedItemPosition){
                    0->{
                        w = mainViewModel.normal(tt)
                        tn = mainViewModel.getTodayName(tt)
                        val y = mainViewModel.getYesterday(tt)
                        sectionsPagerAdapter?.dateFunc(y, w, choose[selectedItemPosition])
                        text_data_cash_box.text = tn
                    }
                    1->{
                        val today = mainViewModel.normal(tt)
                        w = mainViewModel.getLastWeek(tt)
                        sectionsPagerAdapter?.dateFunc(w, today, choose[selectedItemPosition])
                        text_data_cash_box.text = "$w - $today"
                    }
                    2->{
                        val today = mainViewModel.normal(tt)
                        w = mainViewModel.getLastMonth(tt)
                        sectionsPagerAdapter?.dateFunc(w, today, choose[selectedItemPosition])
                        text_data_cash_box.text = "$w - $today"
                    }
                    3->{
                        setupRangePickerDialog()
                    }
                }
                val toast = Toast.makeText(requireActivity().applicationContext, "Выбрано: " + choose[selectedItemPosition], Toast.LENGTH_SHORT)
                toast.show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setupRangePickerDialog() {
        val builderRange = MaterialDatePicker.Builder.dateRangePicker()
        builderRange.setCalendarConstraints(limitRange().build())
        val pickerRange = builderRange.build()
        pickerRange.show(childFragmentManager, pickerRange.toString())
        pickerRange.addOnPositiveButtonClickListener {
            val sdn = mainViewModel.getDate(it.first!!.toLong())
            val edn = mainViewModel.getDate(it.second!!.toLong())
            val sdc = mainViewModel.normal(sdn)
            val edc = mainViewModel.normal(edn)
            startDate = sdc
            endDate = edc
            sectionsPagerAdapter?.dateFunc(sdc, edc, "За период")
            text_data_cash_box.text = "$sdn - $edn"
        }
    }
}