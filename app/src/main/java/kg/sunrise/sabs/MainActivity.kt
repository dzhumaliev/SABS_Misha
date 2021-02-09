package kg.sunrise.sabs
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kg.sunrise.sabs.ui.still.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme2)
        setContentView(R.layout.activity_main)
//        if (UserInfoPref.getAccessToken(GettingStartApplication.instance) == null)
//            startActivity(Intent(this, LoginActivity::class.java))
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_cash_box, R.id.navigation_receipt, R.id.navigation_reports, R.id.navigation_still ))
        navView.setupWithNavController(navController)
        init()
    }
    private fun init(){
        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.popup_menu -> {
                    showPopup(view)
                }
            }
        }
        popup_menu.setOnClickListener(clickListener)
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
    private fun showPopup(view: View) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(this, view)
        popup.inflate(R.menu.menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.action1 -> {
                    startActivity(Intent(this, SettingActivity::class.java))
                }
                R.id.action2 -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show();
                }
                R.id.action3 -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show();
                }
            }
            true
        })
        popup.show()
    }
}
