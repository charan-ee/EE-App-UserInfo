package com.example.userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInputFragment = UserInputFragment()

        val userInputTransaction = supportFragmentManager.beginTransaction()
        userInputTransaction.replace(R.id.main_container, userInputFragment)
        userInputTransaction.commit()
    }
}