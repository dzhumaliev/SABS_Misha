package kg.sunrise.sabs.data.model

data class CancelsModel(
    val succes : Boolean,
    val data: ArrayList<CancelsData>
)
data class CancelsData(
    val id: Int = 0,
    var time: String = "",
    var dishName: String = "",
    var cancelUserName: String = "",
    var billUserName: String = ""

)