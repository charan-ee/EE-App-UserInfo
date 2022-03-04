package com.android.userinfo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.userinfo.databinding.ActivityInfoDisplayBinding
import java.lang.String.format

class InfoDisplayActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_display)

        val binding = ActivityInfoDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val infoBundle = intent.extras

        val username = infoBundle?.getString(USERNAME)
        val email = infoBundle?.getString(EMAIL)
        val phone = infoBundle?.getString(EMAIL)
        val pincode = infoBundle?.getString(EMAIL)
        val address = infoBundle?.getString(ADDRESS)

        val userInfoText = format(getString(R.string.userinfo_text), username, address, pincode, phone, email)

        binding.textViewUserInfo.setText(userInfoText)
    }
}