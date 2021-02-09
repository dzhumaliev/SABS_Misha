package kg.sunrise.sabs.utils
import com.google.android.material.datepicker.CalendarConstraints
import kg.sunrise.sabs.ui.cash_box.CashBoxViewModel
import kg.sunrise.sabs.ui.receipt.ReceiptViewModel
import kg.sunrise.sabs.ui.reports.ReportsViewModel
import kg.sunrise.sabs.ui.still.StillViewModel
import java.util.*

lateinit var cahsBoxViewModel: CashBoxViewModel
lateinit var reportsViewModel: ReportsViewModel
lateinit var receiptViewModel: ReceiptViewModel
lateinit var stillViewModel: StillViewModel

var sD : String = ""
var eD : String = ""

var w: String = ""

var tt: String = ""//сегодня dd/MM/yyyy
var today: String = ""//сегодня MM/dd/yyyy
var tn: String = ""//сегодня dd MMMM

var y: String = ""//вчера MM/dd/yyyy
var wn: String = ""//прошлая неделя dd/MM/yyyy
var mn: String = ""//прошлый месяц dd/MM/yyyy

var day: Int = 0
var month: Int = 0
var year: Int = 0

var calendar_start: String = ""
var calendar_end: String = ""

var CATEGORY_ID: String = "date"

var startDate: String = ""
var endDate: String = ""
var spinner: String = ""

//кнопки
var a: Boolean = false
var b: Boolean = false

fun limitRange(): CalendarConstraints.Builder {
    val constraintsBuilderRange = CalendarConstraints.Builder()
    val calendarStart: Calendar = Calendar.getInstance()
    val calendarEnd: Calendar = Calendar.getInstance()
    val startYear = 1990
    val startMonth = 1
    val startDate = 1
    val endYear = year
    val endMonth = month
    val endDate = day
    calendarStart.set(startYear, startMonth - 1, startDate - 1)
    calendarEnd.set(endYear, endMonth - 1, endDate)
    val minDate = calendarStart.timeInMillis
    val maxDate = calendarEnd.timeInMillis
    constraintsBuilderRange.setStart(minDate)
    constraintsBuilderRange.setEnd(maxDate)
    constraintsBuilderRange.setValidator(RangeValidator(minDate, maxDate))
    return constraintsBuilderRange
}


