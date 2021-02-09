package kg.sunrise.sabs.data.model

data class PaymentsModel(
    val succes : Boolean,
    val data: ArrayList<PaymentsData>
)
data class PaymentsData(
    val id: Int = 0,
    var cafeid: Int = 0,
    var operid: Int = 0,
    var doC_TYPE: Int = 0,
    var amount: Int = 0,
    var desK_ID: Int = 0,
    var partnername: Int = 0,
    var username: String = "",
    var operationname: String = "",
    var assetname: String = "",
    var dt: String = "",
    var comment: Int = 0,
    var lastUpdate: Int = 0,
    var userid: Int = 0,
    var cafename: Int = 0,
    var forsend: Int = 0
)