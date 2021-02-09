package kg.sunrise.sabs.data
import kg.sunrise.sabs.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface SecondApi {

    @GET("Report/cashBoxStatistics")
    fun getCashBoxStatistics(@Query("startDate") o: String, @Query("endDate") t: String): Call<CashBoxModel>

    @GET("Report/cashBoxByTime")
    fun getCashBoxByTime(@Query("startDate") o: String, @Query("endDate") t: String): Call<CashBoxByTimeModel>



    @GET("Report/bills")
    fun getBills(@Query("startDate") o: String, @Query("endDate") t: String): Call<BillsModel>







    @GET("Report/cancelDishes")// отмены на день
    fun getCancels(@Query("startDate") o: String, @Query("endDate") t: String): Call<CancelsModel>

    @GET("Report/cancelDishesOfDate")//отмены по дням
    fun getCancelsDate(@Query("startDate") o: String, @Query("endDate") t: String): Call<CancelDishesOfDayModel>

    @GET("Report/cancelDishesOfDay")//внутренняя станица отмененных
    fun getCancelsDay(@Query("dateOfCancel") o: String): Call<CancelsModel>



    @GET("Report/topSellingDishes")//топ продаваемых
    fun getTopSelling(@Query("startDate") o: String, @Query("endDate") t: String): Call<TopSellingModel>



    @GET("Report/dishesSoldOfDate")//проданных по дням
    fun getDishesSoldOfDate(@Query("startDate") o: String, @Query("endDate") t: String): Call<DishesSoldDateModel>

    @GET("Report/dishesSoldOfDay")//внутренняя станица проданных
    fun getDishesSoldOfDay(@Query("dateOfSold") o: String): Call<DishesSoldOfDayModel>





    @GET("Report/payments")
    fun getPayments(@Query("startDate") o: String, @Query("endDate") t: String): Call<PaymentsModel>

    @GET("Report/test")
    fun getReport(@Query("startDate") o: String, @Query("endDate") t: String): Call<ReportModel>
}