package com.gosnomerKZ.presentation.main

import android.os.Bundle
import android.text.InputFilter
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.gosnomerKZ.BuildConfig
import com.gosnomerKZ.R
import com.gosnomerKZ.parser.TextParser
import com.gosnomerKZ.databinding.ActivityMainBinding
import com.gosnomerKZ.numbers.NumberFactory
import java.util.*


class MainActivity : AppCompatActivity() {

    private val viewModel =
        MainViewModel(MainCommunication.Base(), PriceCommunication.Base(), NumberFactory.Base())

    private lateinit var binding: ActivityMainBinding

    private fun setTextSwitcher() {
        binding.priceText.setFactory {
            TextView(
                ContextThemeWrapper(this@MainActivity, null), null, 0
            ).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                text = "Цена 0 тенге (Примерно 0 МРП)"
            }
        }
        val inAnim = AnimationUtils.loadAnimation(
            this,
            android.R.anim.fade_in
        )
        val outAnim = AnimationUtils.loadAnimation(
            this,
            android.R.anim.fade_out
        )
        inAnim.duration = 200
        outAnim.duration = 200
        binding.priceText.inAnimation = inAnim
        binding.priceText.outAnimation = outAnim
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.let {
            title = getString(R.string.appbar_name)
        }

        viewModel.updateMrp(Firebase.database.reference)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextSwitcher()

        initBannerAd()

        binding.numberField.filters += InputFilter.AllCaps()

        val textParser = TextParser.Base()
        textParser.setEditText(binding.numberField)

        viewModel.observe(this) {
            binding.priceText.setText(getString(R.string.result_placeholder, it.first, it.second))
        }

        val year = Calendar.getInstance().get(Calendar.YEAR).toString()

        viewModel.priceObserve(this) {
            binding.mrpText.text = getString(
                R.string.price_mrp,
                year,
                it
            )
        }

        binding.calculateButton.setOnClickListener {
            val number = binding.numberField.text.toString()
            if (number.isEmpty() || number.length < 8) {
                binding.numberField.error = getString(R.string.error)
            } else viewModel.calculate(binding.numberField.text.toString())
        }
    }

    private lateinit var adView: AdView

    private fun initBannerAd() {
        adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = BuildConfig.AD_BANNER_ID

        adView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.TOP or Gravity.CENTER_HORIZONTAL
        )

        binding.root.addView(adView)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest)
    }

    public override fun onPause() {
        adView.pause()
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        adView.resume()
    }

    public override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

}


