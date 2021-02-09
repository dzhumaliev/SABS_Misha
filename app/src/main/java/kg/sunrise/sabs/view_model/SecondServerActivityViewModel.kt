package kg.sunrise.sabs.view_model
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.sunrise.sabs.data.SecondRepo
import kg.sunrise.sabs.data.model.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SecondApiActivityViewModel(private val secondRepo: SecondRepo) : ViewModel() {
    var cbsResponseLiveData = MutableLiveData<CashBoxModel>()
    var cbtResponseLiveData = MutableLiveData<CashBoxByTimeModel>()
    var billsResponseLiveData = MutableLiveData<BillsModel>()

    var cancelsResponseLiveData = MutableLiveData<CancelsModel>()
    var cancelsDateResponseLiveData = MutableLiveData<CancelDishesOfDayModel>()
    var cancelsDayResponseLiveData = MutableLiveData<CancelsModel>()

    var topSellingResponseLiveData = MutableLiveData<TopSellingModel>()

    var dishesSoldDateResponseLiveData = MutableLiveData<DishesSoldDateModel>()
    var dishesSoldDayResponseLiveData = MutableLiveData<DishesSoldOfDayModel>()




    var paymentsResponseLiveData = MutableLiveData<PaymentsModel>()
    var reportResponseLiveData = MutableLiveData<ReportModel>()

    fun getCashBoxStatistics(startDate: String, endDate: String) {
        secondRepo.getCashBoxStatistics(cbsResponseLiveData, startDate, endDate)
    }

    fun getCashBoxByTime(startDate: String, endDate: String) {
        secondRepo.getCashBoxByTime(cbtResponseLiveData, startDate, endDate)
    }

    fun getBills(startDate: String, endDate: String) {
        secondRepo.getBills(billsResponseLiveData, startDate, endDate)
    }
    fun getCancelsDate(startDate: String, endDate: String) {
        secondRepo.getCancelsDate(cancelsDateResponseLiveData, startDate, endDate)
    }
    fun getCancelsDay(startDate: String) {
        secondRepo.getCancelsDay(cancelsDayResponseLiveData, startDate)
    }

    fun getTopSelling(startDate: String, endDate: String) {
        secondRepo.getTopSelling(topSellingResponseLiveData, startDate, endDate)
    }

    fun getDishesSoldDate(startDate: String, endDate: String) {
        secondRepo.getDishesSoldDate(dishesSoldDateResponseLiveData, startDate, endDate)
    }
    fun getDishesSoldDay(startDate: String) {
        secondRepo.getDishesSoldDay(dishesSoldDayResponseLiveData, startDate)
    }

    fun getPayments(startDate: String, endDate: String) {
        secondRepo.getPayments(paymentsResponseLiveData, startDate, endDate)
    }
//    fun getReport(startDate: String, endDate: String) {
//        firstRepo.getReport(reportResponseLiveData, startDate, endDate)
//    }

    @SuppressLint("SimpleDateFormat")
    fun getYesterday(sD: String): String {
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null
        try {
            date = formatter.parse(sD)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = Objects.requireNonNull(date)!!.time - 1000 * 60 * 60 * 24
        val postFormater = SimpleDateFormat("MM/dd/yyyy")
        return postFormater.format(diff)
    }
    @SuppressLint("SimpleDateFormat")
    fun getTodayName(sD: String): String {
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null
        try {
            date = formatter.parse(sD)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = Objects.requireNonNull(date)!!.time - 1000 * 60 * 60 * 24
        val postFormater = SimpleDateFormat("dd MMMM")
        return postFormater.format(diff)
    }

    @SuppressLint("SimpleDateFormat")
    fun getMonthName(sD: String): String {
        val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy")
        var date: Date? = null
        try {
            date = formatter.parse(sD)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = Objects.requireNonNull(date)!!.time
        val postFormater = SimpleDateFormat("dd/MM/yyyy")
        return postFormater.format(diff)
    }

    @SuppressLint("SimpleDateFormat")
    fun getLastWeek(sD: String): String {
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null
        try {
            date = formatter.parse(sD)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = Objects.requireNonNull(date)!!.time - 1000 * 60 * 60 * 24 * 7
        val postFormater = SimpleDateFormat("MM/dd/yyyy")
        return postFormater.format(diff)
    }

    @SuppressLint("SimpleDateFormat")
    fun getLastMonth(sD: String): String {
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null
        try {
            date = formatter.parse(sD)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        @Suppress("INTEGER_OVERFLOW") val diff =
            Objects.requireNonNull(date)!!.time - 1296000000 - 1296000000
        val postFormater = SimpleDateFormat("MM/dd/yyyy")
        return postFormater.format(diff)
    }

    @SuppressLint("SimpleDateFormat")
    fun normal(data: String): String {
        val form = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null
        try {
            date = form.parse(data)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val postFormater = SimpleDateFormat("MM/dd/yyyy")
        return postFormater.format(date)
    }
    @SuppressLint("SimpleDateFormat")
   fun getDate(millis: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        return simpleDateFormat.format(millis)
    }
}

//    @SuppressLint("SimpleDateFormat")
//    fun convertHH(data: String): String {
//        val form = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//        var date: Date? = null
//        try {
//            date = form.parse(data)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        val postFormater = SimpleDateFormat("HH")
//        return postFormater.format(date)
//    }
//}

//    @SuppressLint("SimpleDateFormat")
//    fun getNextDay(sD: String): String {
//        val formatter: DateFormat = SimpleDateFormat("MM/dd/yyyy")
//        var date: Date? = null
//        try {
//            date = formatter.parse(sD)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        @Suppress("INTEGER_OVERFLOW") val diff = Objects.requireNonNull(date)!!.time - 1000*60*60*24
//        val postFormater = SimpleDateFormat("EE")
//        return postFormater.format(diff)
//    }
//}