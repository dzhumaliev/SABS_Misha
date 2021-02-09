package kg.sunrise.sabs.ui.restaurant
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.billing.RestaurantModel
import kg.sunrise.sabs.ui.restaurant.adapter.RestourantAdapter
import kg.sunrise.sabs.ui.still.setting.EditRestaurantActivity
import kg.sunrise.sabs.view_model.ApiActivityViewModel
import kotlinx.android.synthetic.main.activity_restaurant2.*
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantActivity : AppCompatActivity() {
    private lateinit var adapterRestaurant: RestourantAdapter
    private val mainViewModel: ApiActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant2)
        init()
    }
    private fun init(){
        restaurant()
        constraint_add_restaurant_text.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, EditRestaurantActivity::class.java))
        })
    }
    private fun restaurant() {
        restaurantConfigureViewModel()
        mainViewModel.getRestaurant()
        restaurantConfigureViews()
    }
    private fun restaurantConfigureViewModel() {
        mainViewModel.restaurantResponseLiveData.observe(this, androidx.lifecycle.Observer {
            adapterRestaurant.swapData(it)
        })
    }
    private fun restaurantConfigureViews() {
        adapterRestaurant = RestourantAdapter()
        val layoutManager = LinearLayoutManager(this)
        recycler_restaurant.layoutManager = layoutManager
        recycler_restaurant.adapter = adapterRestaurant
        adapterRestaurant.setClickListener(object : RestourantAdapter.OnClickListener {
            override fun onItemPositionClick(title: RestaurantModel, position: Int) {
//                startActivity(
//                    Intent(this@SubListActivity, ArabActivity::class.java)
//                    .putExtra(POSITION, position)
//                    .putExtra(LIST, list_image)
//                    .putExtra(CATEGORY_ID, title.id))
            }
        })
    }
}