package kg.sunrise.sabs.ui.still
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import kg.sunrise.sabs.R
import kg.sunrise.sabs.ui.restaurant.adapter.RestourantAdapter
import kg.sunrise.sabs.ui.still.setting.SettingActivity
import kg.sunrise.sabs.view_model.ApiActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class StillFragment : Fragment() {
    private lateinit var adapterRestaurant: RestourantAdapter
    private val mainViewModel: ApiActivityViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_still, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        init()
    }
//    private fun init() {
//        val clickListener = View.OnClickListener { view ->
//            when (view.id) {
//                R.id.popup_menu -> {
//                    showPopup(view)
//                }
//            }
//        }
//        popup_menu.setOnClickListener(clickListener)
//        user()
//        restaurant()
//    }
//    private fun user() {
//        userConfigureViewModel()
//        mainViewModel.getUser()
//    }

//    private fun restaurant() {
//        restaurantConfigureViewModel()
//        mainViewModel.getRestaurant()
//        restaurantConfigureViews()
//    }
//    private fun userConfigureViewModel() {
//        mainViewModel.userResponseLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
//            Log.e("токен", ""+it)
//            mail.text = it.email
//        })
//    }
//    private fun restaurantConfigureViewModel() {
//        mainViewModel.restaurantResponseLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
//            Log.e("токен", ""+it)
//            adapterRestaurant.swapData(it)
//        })
//    }

//    private fun restaurantConfigureViews() {
//        adapterRestaurant = RestourantAdapter()
//        val layoutManager = LinearLayoutManager(activity)
//        profile_recycler.layoutManager = layoutManager
//        profile_recycler.adapter = adapterRestaurant
//    }

    private fun showPopup(view: View) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(activity, view)
        popup.inflate(R.menu.menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.action1 -> {
                    startActivity(Intent(requireActivity(), SettingActivity::class.java))
                }
                R.id.action2 -> {
                    Toast.makeText(activity, item.title, Toast.LENGTH_SHORT).show();
                }
                R.id.action3 -> {
                    Toast.makeText(activity, item.title, Toast.LENGTH_SHORT).show();
                }
            }
            true
        })
        popup.show()
    }
}

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        val inflater = requireActivity().menuInflater
//        inflater.inflate(R.menu.menu, menu)
//        return
//    }
//    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
//        R.id.action1 -> {
//            val toast = Toast.makeText(
//                requireActivity().applicationContext,
//                "Выбрано: " + R.id.action1,
//                Toast.LENGTH_SHORT
//            )
//            true
//        }
//        R.id.action2 -> {
//            val toast = Toast.makeText(
//                requireActivity().applicationContext,
//                "Выбрано: " + R.id.action2,
//                Toast.LENGTH_SHORT
//            )
//            true
//        }
//        R.id.action3 -> {
//            val toast = Toast.makeText(
//                requireActivity().applicationContext,
//                "Выбрано: " + R.id.action2,
//                Toast.LENGTH_SHORT
//            )
//            true
//        }
//        else -> {
//            // If we got here, the user's action was not recognized.
//            // Invoke the superclass to handle it.
//            super.onOptionsItemSelected(item)
//        }
//    }
//}
