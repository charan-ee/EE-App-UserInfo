package com.android.userinfo

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class Validator {

    fun validateAllFields(layout: ConstraintLayout,context: Context): Boolean {
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText && v.text.trim().isEmpty()) {
                val name = v.getTag().toString()
                getToastMessage(context,name + context.getString(R.string.toast_mandatory))
                return false
            }
        }
        return true
    }


    fun validateEmailField(email: String, context: Context): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        getToastMessage(context, context.getString(R.string.toast_email))
        return false
    }

    fun validatePincodeField(pincode: String, context: Context): Boolean {
        if (pincode.length != 6) {
            getToastMessage(context, context.getString(R.string.toast_pincode))
            return false
        }
        return true
    }

    internal fun validatePhoneField(phoneNumber: String, context: Context): Boolean {
        if (phoneNumber.length != 10) {
            getToastMessage(context, context.getString(R.string.toast_phone))
            return false
        }
        return true
    }

    private fun getToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}