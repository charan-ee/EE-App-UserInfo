package com.example.userinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.userinfo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textUsername = binding.editTextViewUserName.text

        val cancelConfirmButtonLayout = binding.buttonLayout
        val cancelButtonView = binding.buttonCancel
        val confirmButton = binding.buttonConfirm

        val editTextUsername = findViewById<EditText>(R.id.editTextViewUserName)
        val editTextEmail = findViewById<EditText>(R.id.editTextViewEmail)
        val editTextPhone = findViewById<EditText>(R.id.editTextViewPhone)
        val editTextPincode = findViewById<EditText>(R.id.editTextViewPincode)
        val editTextAddress = findViewById<EditText>(R.id.editTextViewAddress)

        val buttonValidate = findViewById<Button>(R.id.buttonValidate)
        val layout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        buttonValidate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val isAllFieldsFilled = validateAllFields(layout)
                if (isAllFieldsFilled
                    && validatePhoneField(editTextPhone)
                    && validatePincodeField(editTextPincode)
                    && validateEmailField(editTextEmail)
                ) {
                    buttonValidate.visibility = View.INVISIBLE
                    cancelConfirmButtonLayout.visibility = View.VISIBLE
                    disableEditViews()
                } else {
//                    startActivity(getIntent())
                }
            }

            private fun disableEditViews() {
                for (i in 0 until layout.childCount) {
                    val v = layout.getChildAt(i)
                    if (v is EditText) {
                        v.isEnabled = false
                        v.setTextColor(Color.BLACK)
                    }
                }
            }
        })

        confirmButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val bundle = Bundle()
                bundle.putString("username", editTextUsername.text.toString())
                bundle.putString("email", editTextEmail.text.toString())
                bundle.putString("phone", editTextPhone.text.toString())
                bundle.putString("pincode", editTextPincode.text.toString())
                bundle.putString("address", editTextAddress.text.toString())

                val intent = Intent(this@MainActivity , InfoDisplayActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

    private fun validateEmailField(view: EditText): Boolean {
        val email = view.text.toString()
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        getToastMessage("Email should have @ and .com or co.in")
        return false
    }

    private fun validateAllFields(layout: ConstraintLayout): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText && v.text.trim().isEmpty()) {
                val name = v.getTag().toString()
                getToastMessage("$name is mandatory")
                return false
            }
        }
        return true
    }

    private fun validatePincodeField(view: EditText): Boolean {
        val pincode = view.text.toString()
        if (pincode.length != 6) {
            getToastMessage("Pincode should be only length of 6")
            return false
        }
        return true
    }

    private fun validatePhoneField(view: EditText): Boolean {
        val phoneNumber = view.text.toString()
        if (phoneNumber.length != 10) {
            getToastMessage("Phone number should be only length of 10")
            return false
        }
        return true
    }

    private fun getToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}