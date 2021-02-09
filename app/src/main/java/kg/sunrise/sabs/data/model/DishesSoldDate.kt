package kg.sunrise.sabs.data.model

data class DishesSoldDateModel(
    val succes : Boolean,
    val data: ArrayList<DishesSoldDateData>
)
data class DishesSoldDateData(
    val id: Int = 0,
    var soldDishesDate: String = "",
    var soldDishesDateAndMonth: String = "",
    var amountOfSoldDishes: String = ""
)