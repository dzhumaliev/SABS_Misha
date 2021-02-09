package kg.sunrise.sabs.data.model

data class TopSellingModel(
    val succes : Boolean,
    val data: ArrayList<TopSellingData>
)
data class TopSellingData(
    val id: Int = 0,
    var dishName: String = "",
    var percentCount: Float = 0.0F
)