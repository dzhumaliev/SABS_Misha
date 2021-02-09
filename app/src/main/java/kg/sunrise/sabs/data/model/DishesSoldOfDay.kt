package kg.sunrise.sabs.data.model

data class DishesSoldOfDayModel(
    val succes : Boolean,
    val data: ArrayList<DishesSoldOfDayData>
)
data class DishesSoldOfDayData(
    val id: Int = 0,
    var totalAmount: String = "",
    var dish: String = "",
    var userName: String = "",
    var price: String = "",
    var timeOfOperation: String = ""
)