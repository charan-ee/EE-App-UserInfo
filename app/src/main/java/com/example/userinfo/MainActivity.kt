package com.example.userinfo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.userinfo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
                val isAllFieldsFilled = validateAllFields(layout)
                if (isAllFieldsFilled
                    && validatePhoneField(editTextPhone)
                    && validatePincodeField(editTextPincode,this@MainActivity)
                    && validateEmailField(editTextEmail)
                ) {
                    buttonValidate.visibility = View.INVISIBLE
                    cancelConfirmButtonLayout.visibility = View.VISIBLE
                    disableEditViews()
                } else {
                    startActivity(getIntent())
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

        confirmButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val bundle = Bundle()
                bundle.putString(USERNAME, editTextUsername.text.toString())
                bundle.putString(EMAIL, editTextEmail.text.toString())
                bundle.putString(PHONE, editTextPhone.text.toString())
                bundle.putString(PINCODE, editTextPincode.text.toString())
                bundle.putString(ADDRESS, editTextAddress.text.toString())

                val intent = Intent(this@MainActivity, InfoDisplayActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

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

    private fun validateEmailField(view: EditText): Boolean {
        val email = view.text.toString()
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        getToastMessage(TOAST_EMAIL)
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

    private fun validatePincodeField(view: EditText, context: Context): Boolean {
        val pincode = view.text.toString()
        if (pincode.length != 6) {
            getToastMessage(TOAST_PINCODE)
            return false
        }
        return true
    }

    internal fun validatePhoneField(view: EditText): Boolean {
        val phoneNumber = view.text.toString()
        if (phoneNumber.length != 10) {
            getToastMessage(TOAST_PHONE)
            return false
        }
        return true
    }

    private fun getToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

}