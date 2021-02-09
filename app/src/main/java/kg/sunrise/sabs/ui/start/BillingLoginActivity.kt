package kg.sunrise.sabs.ui.start
import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kg.sunrise.sabs.R
import kg.sunrise.sabs.ui.restaurant.RestaurantActivity
import kg.sunrise.sabs.utils.UserInfoPref
import kg.sunrise.sabs.view_model.ApiActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class BillingLoginActivity : AppCompatActivity() {
    private val MULTIPLE_PERMISSION_REQUEST_CODE = 12345
    private val currentApiVersion = Build.VERSION.SDK_INT
    private val mainViewModel: ApiActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.isEnabled = false
        mail()
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                prn()
            }
        }

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
                    btn_login.background = resources.getDrawable(R.drawable.button_login_true)
                    btn_login.isEnabled = true
                    btn_login.setOnClickListener(View.OnClickListener {
                        post(login.text.toString(), password.text.toString())
                    })
                } else {
                    btn_login.background = resources.getDrawable(R.drawable.button_login_false)
                }
            }
        })
    }
    private fun post(email: String, password: String) {
        loginConfigureViewModel()
        mainViewModel.toLogin(email, password)
    }
    private fun loginConfigureViewModel() {
        mainViewModel.loginResponseLiveData.observe(this, androidx.lifecycle.Observer {
            Log.e("токен", "Bearer"+it.token)
            UserInfoPref.setUserToken(this, "Bearer "+it.token)
            startActivity(Intent(this, RestaurantActivity::class.java))
        })
    }

    private fun prn() {
        val builder =
            AlertDialog.Builder(this)
        builder.setTitle("дать разрешение на чтение")
            .setMessage("") //.setIcon(R.drawable.ic_android_cat)
            .setCancelable(true)
            .setPositiveButton(
                "Нет"
            ) { dialog, which -> }
            .setNegativeButton(
                "Да"
            ) { dialog, id ->
                if (currentApiVersion >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    } else {
                        checkPermissionsState()
                    }
                }
            }
        val alert = builder.create()
        alert.show()
        alert.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.colorBlue))
        alert.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.colorBlue))
    }
    private fun checkPermissionsState() {
        val cameraStatePermissionCheck = ContextCompat.checkSelfPermission(
            this,
            permission.CAMERA
        )
        val writeExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(
            this,
            permission.WRITE_EXTERNAL_STORAGE
        )
        val readExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(
            this,
            permission.READ_EXTERNAL_STORAGE
        )
        if (cameraStatePermissionCheck == PackageManager.PERMISSION_GRANTED && writeExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED && readExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("CheckPermission", "TRUE")
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    permission.CAMERA,
                    permission.READ_EXTERNAL_STORAGE,
                    permission.WRITE_EXTERNAL_STORAGE
                ),
                MULTIPLE_PERMISSION_REQUEST_CODE
            )
        }
    }
}

