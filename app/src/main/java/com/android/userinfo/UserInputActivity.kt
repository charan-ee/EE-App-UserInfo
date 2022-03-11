package com.android.userinfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.userinfo.databinding.ActivityMainBinding



class UserInputActivity : AppCompatActivity() {
    private lateinit var validator: Validator
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validator = Validator()


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextUsername = binding.editTextViewUserName
        val editTextEmail = binding.editTextViewEmail
        val editTextPhone = binding.editTextViewPhone
        val editTextPincode = binding.editTextViewPincode
        val editTextAddress = binding.editTextViewAddress

        val cancelConfirmButtonLayout = binding.buttonLayout
        val cancelButton = binding.buttonCancel
        val confirmButton = binding.buttonConfirm
        val buttonValidate = binding.buttonValidate
        val layout = binding.constraintLayout

        buttonValidate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                
                val isAllFieldsFilled = validator.validateAllFields(layout,this@UserInputActivity)
                if (isAllFieldsFilled
                    && validator.validatePhoneField(editTextPhone.text.toString(), this@UserInputActivity)
                    && validator.validatePincodeField(editTextPincode.text.toString(),this@UserInputActivity)
                    && validator.validateEmailField(editTextEmail.text.toString(),this@UserInputActivity)
                ) {
                    buttonValidate.visibility = View.INVISIBLE
                    cancelConfirmButtonLayout.visibility = View.VISIBLE
                    disableEditViews()
                } else {
                    startActivity(intent)
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

        confirmButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, editTextUsername.text.toString())
            bundle.putString(EMAIL, editTextEmail.text.toString())
            bundle.putString(PHONE, editTextPhone.text.toString())
            bundle.putString(PINCODE, editTextPincode.text.toString())
            bundle.putString(ADDRESS, editTextAddress.text.toString())

            val intent = Intent(this@UserInputActivity, InfoDisplayActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }

        cancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                buttonValidate.visibility = View.VISIBLE
                cancelConfirmButtonLayout.visibility = View.INVISIBLE
                enableEditViews()
            }

            private fun enableEditViews() {
                for (i in 0 until layout.childCount) {
                    val v = layout.getChildAt(i)
                    if (v is EditText) {
                        v.isEnabled = true
                    }
                }
            }
        })
    }



}