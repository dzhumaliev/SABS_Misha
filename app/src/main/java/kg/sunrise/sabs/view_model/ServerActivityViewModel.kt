package kg.sunrise.sabs.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.sunrise.sabs.data.FirstRepo
import kg.sunrise.sabs.data.billing.LoginModel
import kg.sunrise.sabs.data.billing.RestaurantModel
import kg.sunrise.sabs.data.billing.UserModel
import kotlin.collections.ArrayList

class ApiActivityViewModel(private val firstRepo: FirstRepo) : ViewModel() {
    var loginResponseLiveData = MutableLiveData<LoginModel>()
    var restaurantResponseLiveData = MutableLiveData<ArrayList<RestaurantModel>>()
    var userResponseLiveData = MutableLiveData<UserModel>()

    fun toLogin(email: String, password: String) {
        firstRepo.toLogin(loginResponseLiveData, email, password)
    }
    fun getUser() {
        firstRepo.getUser(userResponseLiveData)
    }
    fun getRestaurant() {
        firstRepo.getRestaurant(restaurantResponseLiveData)
    }

}