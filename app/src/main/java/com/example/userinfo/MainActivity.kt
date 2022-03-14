package com.example.userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInputFragment = UserInputFragment()
        val userInputTransaction = supportFragmentManager.beginTransaction()
        userInputTransaction.replace(R.id.main_container, userInputFragment)
        userInputTransaction.commit()

    }
}