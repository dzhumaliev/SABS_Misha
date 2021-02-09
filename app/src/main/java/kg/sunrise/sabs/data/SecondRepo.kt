package kg.sunrise.sabs.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kg.sunrise.sabs.data.billing.LoginModel
import kg.sunrise.sabs.data.billing.RestaurantModel
import kg.sunrise.sabs.data.billing.UserModel
import kg.sunrise.sabs.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondRepo (private val context: Context, private val secondApi: SecondApi) {
    fun getCashBoxStatistics(data: MutableLiveData<CashBoxModel>, starDate: String, endDate: String) {
        secondApi.getCashBoxStatistics(starDate, endDate).enqueue(object : Callback<CashBoxModel> {
            override fun onFailure(call: Call<CashBoxModel>, t: Throwable) {
                Log.e("code", ""+t.message)
            }
            override fun onResponse(
                call: Call<CashBoxModel>,
                response: Response<CashBoxModel>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.e("body", ""+response.body())
                }else {
                    Log.e("message", response.message())
                    Log.e("code", ""+response.code())
                }
            }
        })
    }
    fun getCashBoxByTime(data: MutableLiveData<CashBoxByTimeModel>, starDate: String, endDate: String) {
        secondApi.getCashBoxByTime(starDate, endDate).enqueue(object : Callback<CashBoxByTimeModel> {
            override fun onFailure(call: Call<CashBoxByTimeModel>, t: Throwable) {
                Log.e("code", ""+t.message)
            }
            override fun onResponse(
                call: Call<CashBoxByTimeModel>,
                response: Response<CashBoxByTimeModel>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.e("body", ""+response.body())
                }else {
                    Log.e("message", response.message())
                    Log.e("code", ""+response.code())
                }
            }
        })
    }

    //отмены на дату
    fun getCancelsDate(data: MutableLiveData<CancelDishesOfDayModel>, starDate: String, endDate: String) {
        secondApi.getCancelsDate(starDate, endDate).enqueue(object : Callback<CancelDishesOfDayModel> {
            override fun onFailure(call: Call<CancelDishesOfDayModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<CancelDishesOfDayModel>,
                response: Response<CancelDishesOfDayModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }

    //отмены на день
    fun getCancelsDay(data: MutableLiveData<CancelsModel>, dateOfCancel: String) {
        secondApi.getCancelsDay(dateOfCancel).enqueue(object : Callback<CancelsModel> {
            override fun onFailure(call: Call<CancelsModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<CancelsModel>,
                response: Response<CancelsModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }

    //топ продаваемых
    fun getTopSelling(data: MutableLiveData<TopSellingModel>, starDate: String, endDate: String) {
        secondApi.getTopSelling(starDate, endDate).enqueue(object : Callback<TopSellingModel> {
            override fun onFailure(call: Call<TopSellingModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<TopSellingModel>,
                response: Response<TopSellingModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }
    //проданных блюд на дату
    fun getDishesSoldDate(data: MutableLiveData<DishesSoldDateModel>, starDate: String, endDate: String) {
        secondApi.getDishesSoldOfDate(starDate, endDate).enqueue(object : Callback<DishesSoldDateModel> {
            override fun onFailure(call: Call<DishesSoldDateModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<DishesSoldDateModel>,
                response: Response<DishesSoldDateModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }
    //проданных блюд на день
    fun getDishesSoldDay(data: MutableLiveData<DishesSoldOfDayModel>, dateOfCancel: String) {
        secondApi.getDishesSoldOfDay(dateOfCancel).enqueue(object : Callback<DishesSoldOfDayModel> {
            override fun onFailure(call: Call<DishesSoldOfDayModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<DishesSoldOfDayModel>,
                response: Response<DishesSoldOfDayModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }





    fun getPayments(data: MutableLiveData<PaymentsModel>, starDate: String, endDate: String) {
        secondApi.getPayments(starDate, endDate).enqueue(object : Callback<PaymentsModel> {
            override fun onFailure(call: Call<PaymentsModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<PaymentsModel>,
                response: Response<PaymentsModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }
    fun getReport(data: MutableLiveData<ReportModel>, starDate: String, endDate: String) {
        secondApi.getReport(starDate, endDate).enqueue(object : Callback<ReportModel> {
            override fun onFailure(call: Call<ReportModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<ReportModel>,
                response: Response<ReportModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "SUCCESS")
                    data.value = response.body()
                }else {
                    Log.e("TAG", response.message())
                    Log.e("TAG", ""+response.code())
                }
            }
        })
    }

    fun getBills(data: MutableLiveData<BillsModel>, starDate: String, endDate: String) {
        secondApi.getBills(starDate, endDate).enqueue(object : Callback<BillsModel> {
            override fun onFailure(call: Call<BillsModel>, t: Throwable) {}
            override fun onResponse(
                call: Call<BillsModel>,
                response: Response<BillsModel>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    Log.e("body", ""+response.body())
                }else {
                    Log.e("message", response.message())
                    Log.e("code", ""+response.code())
                }
            }
        })
    }
}
