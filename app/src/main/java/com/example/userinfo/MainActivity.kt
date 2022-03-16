package com.example.userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mainActivityMainBinding.root)

        val userInputFragment = UserInputFragment()

        if(savedInstanceState == null) {
            loadFragment(userInputFragment)
        }
    }

    private fun loadFragment(userInputFragment: UserInputFragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, userInputFragment)
            commit()
        }
    }
}