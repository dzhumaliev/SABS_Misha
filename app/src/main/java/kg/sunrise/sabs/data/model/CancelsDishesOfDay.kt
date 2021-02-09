package kg.sunrise.sabs.data.model

data class CancelDishesOfDayModel(
    val succes : Boolean,
    val data: ArrayList<CancelsDishesOfDayData>
)
data class CancelsDishesOfDayData(
    val id: Int = 0,
    var cancelDishesDate: String = "",
    var cancelDishesDateAndMonth: String = "",
    var amountOfCancelDishes: String = ""
)