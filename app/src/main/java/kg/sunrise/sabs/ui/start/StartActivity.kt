package kg.sunrise.sabs.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import kg.sunrise.sabs.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        Glide.with(this).load(R.drawable.ic_logo).into(image)
        val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.clockwise)
        image.startAnimation(animation)
        slide()
    }
    private fun slide(){
        mHandler = Handler()
        mRunnable = Runnable {
//            startActivity(Intent(this@StartActivity, BillingLoginActivity::class.java))
            startActivity(Intent(this@StartActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.one, R.anim.two)
        }
        mHandler.postDelayed(mRunnable, 1000*2*1)
    }
}
