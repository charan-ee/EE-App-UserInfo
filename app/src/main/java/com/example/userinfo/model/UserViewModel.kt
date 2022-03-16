package com.example.userinfo.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
//    var userModel = MutableLiveData<UserModel>()

    private val _username = MutableLiveData<String>("")
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _pincode = MutableLiveData<String>()
    val pincode: LiveData<String> = _pincode

    private val _address = MutableLiveData("")
    val address: LiveData<String> = _address

    private val _isValid = MutableLiveData(false)
    val isValid: LiveData<Boolean> = _isValid

    private val _isVisible = MutableLiveData(false)
    val isVisible: LiveData<Boolean> = _isVisible

    private val _isEnable = MutableLiveData(true)
    val isEnable: LiveData<Boolean> = _isEnable

    fun setIsValid(isValid: Boolean) {
        this._isValid.value = isValid
    }

    fun setVisibility(visible: Boolean) {
        _isVisible.value = visible
    }

    fun setIsEnable(enable: Boolean) {
        _isEnable.value = enable
    }

    fun setUserDetails(
        userName: String,
        email: String,
        phone: String,
        pincode: String,
        address: String
    ) {
        this._username.postValue(userName)
        this._email.postValue(email)
        this._phone.postValue(phone)
        this._pincode.postValue(pincode)
        this._address.postValue(address)
    }
}