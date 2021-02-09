package kg.sunrise.sabs.data
import android.content.Context
import androidx.lifecycle.MutableLiveData
import kg.sunrise.sabs.data.billing.LoginModel
import kg.sunrise.sabs.data.billing.RestaurantModel
import kg.sunrise.sabs.data.billing.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstRepo (private val context: Context, private val firstApi: FirstApi) {
    fun toLogin(data: MutableLiveData<LoginModel>, email: String, password: String) {
        firstApi.toLogin(email, password).enqueue(object : Callback<LoginModel> {
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
            }
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }else {
                }
            }
        })
    }

    fun getUser(data: MutableLiveData<UserModel>) {
        firstApi.getUser().enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
            }
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }else {
                }
            }
        })
    }
    fun getRestaurant(data: MutableLiveData<ArrayList<RestaurantModel>>) {
        firstApi.getRestaurant().enqueue(object : Callback<ArrayList<RestaurantModel>> {
            override fun onFailure(call: Call<ArrayList<RestaurantModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<ArrayList<RestaurantModel>>, response: Response<ArrayList<RestaurantModel>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }else {
                }
            }
        })
    }
}
