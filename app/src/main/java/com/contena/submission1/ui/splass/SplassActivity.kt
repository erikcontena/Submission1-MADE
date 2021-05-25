package com.contena.submission1.ui.splass

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.contena.submission1.R
import com.contena.submission1.ui.home.HomeActivity

class SplassActivity : AppCompatActivity() {
    companion object{
        private const val TIME_DELAY = 2000L
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splass)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this,
                    HomeActivity::class.java)
            )
            finish()

        }, TIME_DELAY)

    }
}

