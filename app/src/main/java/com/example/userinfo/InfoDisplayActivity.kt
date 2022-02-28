package com.example.userinfo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class InfoDisplayActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_display)

        val textViewUserInfo = findViewById<TextView>(R.id.textViewUserInfo)

        val infoBundle = intent.extras

        val username = infoBundle?.getString("username")
        val email = infoBundle?.getString("email")
        val phone = infoBundle?.getString("phone")
        val pincode = infoBundle?.getString("pincode")
        val address = infoBundle?.getString("address")

        textViewUserInfo.setText("Hi $username, How are you? Are you staying at $address-$pincode \n" +
                ". I am not able to contact you on $phone. Can I email you the details at $email")
    }
}