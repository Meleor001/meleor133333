package com.gosnomerKZ.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gosnomerKZ.App
import com.gosnomerKZ.R
import com.gosnomerKZ.presentation.main.MainActivity

private const val COUNTER_TIME = 3L

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var secondsRemaining: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        createTimer(COUNTER_TIME)
    }

    private fun createTimer(seconds: Long) {
        val countDownTimer: CountDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000 + 1
            }

            override fun onFinish() {
                secondsRemaining = 0

                val application = application as? App

                if (application == null) {
                    Log.e(this::class.java.name, "Failed to cast application to App.")
                    startMainActivity()
                    return
                }

                application.showAdIfAvailable(
                    this@SplashActivity,
                    object : App.OnShowAdCompleteListener {
                        override fun onShowAdComplete() {
                            startMainActivity()
                        }
                    })
            }
        }
        countDownTimer.start()
    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}