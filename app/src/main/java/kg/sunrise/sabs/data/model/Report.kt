package kg.sunrise.sabs.data.model

data class ReportModel(
    val succes : Boolean,
    val data: ArrayList<ReportData>
)
data class ReportData(
    val id: Int = 0,
    val cafeid: Int = 0,
    val operid: Int = 0,
    val doC_TYPE: Int = 0,
    val amount: Int = 0,
    val desK_ID: Int = 0,
    val partnername: Int = 0,
    val username: String = "",
    var operationname: String = "",
    var assetname: String = "",
    var dt: String = "",
    var comment: String = "",
    var lastupdate: String = "",
    var userid: Int = 0,
    var cafename: String = "",
    var forsend: String = ""
) : Comparable<Any> {
    override fun compareTo(other: Any): Int {
        TODO("Not yet implemented")
    }
}