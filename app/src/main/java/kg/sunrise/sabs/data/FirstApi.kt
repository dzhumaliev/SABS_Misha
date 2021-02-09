package kg.sunrise.sabs.data
import kg.sunrise.sabs.data.billing.LoginModel
import kg.sunrise.sabs.data.billing.RestaurantModel
import kg.sunrise.sabs.data.billing.UserModel
import retrofit2.Call
import retrofit2.http.*

interface FirstApi {
    @FormUrlEncoded
    @POST("api-token-auth/")
    fun toLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginModel>

    @GET("user/detail")
    fun getUser(): Call<UserModel>

    @GET("restaurant/")
    fun getRestaurant(): Call<ArrayList<RestaurantModel>>
}