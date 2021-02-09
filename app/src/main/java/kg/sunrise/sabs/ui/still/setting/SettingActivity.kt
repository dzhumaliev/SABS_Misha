package kg.sunrise.sabs.ui.still.setting
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kg.sunrise.sabs.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        init()
    }
    private fun init(){
        title_current_password.visibility = View.GONE
        title_new_password.visibility = View.GONE
        title_confirm_password.visibility = View.GONE
        back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        cp()
        np()
        con()
    }
    private fun cp() {
        current_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (current_password.length() == 0) {
                    title_current_password.visibility = View.GONE
                } else {
                    title_current_password.visibility = View.VISIBLE
                }
            }
        })
    }
    private fun np() {
        new_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (new_password.length() == 0) {
                    title_new_password.visibility = View.GONE
                } else {
                    title_new_password.visibility = View.VISIBLE
                }
            }
        })
    }
    private fun con() {
        confirm_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (confirm_password.length() == 0) {
                    title_confirm_password.visibility = View.GONE
                    btn_save.background = resources.getDrawable(R.drawable.button_login_false)
                } else {
                    title_confirm_password.visibility = View.VISIBLE
                    btn_save.background = resources.getDrawable(R.drawable.button_login_true)
                    btn_save.isEnabled = true
                    btn_save.setOnClickListener(View.OnClickListener {
//                        post(login.text.toString(), password.text.toString())
                    })
                }
            }
        })
    }
    private fun mail() {
        current_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (current_password.length() == 0 || confirm_password == new_password) {
//                    btn_login.setBackgroundColor(resources.getColor(R.color.colorBlue))
                    btn_save.background = resources.getDrawable(R.drawable.button_login_true)
                    btn_save.isEnabled = true
                    btn_save.setOnClickListener(View.OnClickListener {
//                        post(login.text.toString(), password.text.toString())
                    })
                } else {
//                    btn_login.setBackgroundColor(resources.getColor(R.color.colorSilverB))
                    btn_save.background = resources.getDrawable(R.drawable.button_login_false)
                }
            }
        })
    }
}