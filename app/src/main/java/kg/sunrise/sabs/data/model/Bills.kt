package kg.sunrise.sabs.data.model

data class BillsModel(
    val succes : Boolean,
    val data: ArrayList<BillsData>
)
data class BillsData(
    val id: Int = 0,
    var billitemid: Int = 0,
    var cafeid: Int = 0,
    var bilL_ID: Int = 0,
    var billnum: Int = 0,
    var tablename: String = "",//имя_таблицы
    var username: String = "",
    var dt: String = "",//дата
    var hall: String = "",//зал
    var bilL_SUM: Int = 0,//сумма кэша
    var winterest: Int = 0,
    var ginterest: Int = 0,
    var bilL_TOTAL: Int = 0,//всего
    var bilL_DISCOUNT: Int = 0,//бфнктнот скидка
    var cash: Int = 0,// кэш
    var credit: Int = 0, // кредит
    var iteM_NAME: String = "", //вещь
    var iteM_PRICE: Int = 0, //цена
    var iteM_QUANTITY: Int = 0,//количество
    var iteM_DISCOUNT: Int = 0,//скидка
    var iteM_SELL_PRICE: Int = 0, //цена продажи
    var iteM_INTEREST: Int = 0,// интерес
    var iteM_SUM: Int = 0, //сумма
    var clientname: String = "",// имя клиента
    var terminal: String = "",//териминал
    var lastupdate: String = "",//последнее обновление
    var userid: Int = 0,//
    var cafename: Int = 0,//имя кафе
    var forsend: Int = 0//для отправки

)