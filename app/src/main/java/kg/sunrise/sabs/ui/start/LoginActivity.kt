package kg.sunrise.sabs.ui.start
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kg.sunrise.sabs.MainActivity
import kg.sunrise.sabs.R
import kg.sunrise.sabs.utils.UserInfoPref
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.isEnabled = false
        mail()
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun mail() {
        login.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (login.length() >= 7 && (login.text.toString().contains("@mail.ru")||login.text.toString().contains("@gmail.com"))) {
//                    btn_login.setBackgroundColor(resources.getColor(R.color.colorBlue))
                    btn_login.background = resources.getDrawable(R.drawable.button_login_true)
                    btn_login.isEnabled = true
                    btn_login.setOnClickListener(View.OnClickListener {
                        post(login.text.toString(), password.text.toString())
                    })
                } else {
//                    btn_login.setBackgroundColor(resources.getColor(R.color.colorSilverB))
                    btn_login.background = resources.getDrawable(R.drawable.button_login_false)
                }
            }
        })
    }
    private fun post(email: String, password: String){
        val thread = Thread(Runnable {
            try {
                val url = URL("http://5.196.129.104:40003/api/Account/login")
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8")
                conn.setRequestProperty("Accept", "application/json")
                conn.doOutput = true
                conn.doInput = true
                val jsonParam = JSONObject()
                jsonParam.put("email", email)
                jsonParam.put("password", password)
                val os = DataOutputStream(conn.outputStream)
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString())
                os.flush()
                os.close()
                if (conn.responseCode != 200){
                    runOnUiThread {
                        val errorToast = Toast.makeText(this@LoginActivity, "Не удалось войти, проверьте правильность ввода", Toast.LENGTH_SHORT)
                        errorToast.show()
                    }
                }
                val buffer = StringBuffer()
                var reader: BufferedReader? = null
                reader = BufferedReader(InputStreamReader(conn.inputStream))
                when {
                    conn.responseCode.toString() == "200" -> {
                        var line: String = ""
                        while (reader.readLine().also { line = it } != null) {
                            buffer.append(line)
                            UserInfoPref.setUserToken(this, line.replace("{\"success\":true,\"data\":\"", "bearer ").replace("\"}", ""))
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    }
                }
                conn.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


}

//    private fun postlogin(){
//        val jsonParam = JSONObject()
//            jsonParam.put("email", "traceout@mail.ru")
//            jsonParam.put("password", "123")
//        loginViewModel.toLogin(jsonParam)
//    }


