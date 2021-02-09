package kg.sunrise.sabs.data.model


data class CashBoxModel(
    val succes : Boolean,
    val data: CashBoxData
)
data class CashBoxData(
    val averageAmount: String = "",
    var totalAmount: String = "",
    var guestCount: String = "",
    var checkCount: String = "",
    var openTables:String = "",
    var closeTables: String = "",
    var amountOfCashbox: String = ""
)