package kg.sunrise.sabs.data.model

data class CashBoxByTimeModel(
    val succes : Boolean,
    val data: ArrayList<CashBoxByTimeData>
)
data class CashBoxByTimeData(
    val hour: String = "",
    var totalAmount: String = ""
)