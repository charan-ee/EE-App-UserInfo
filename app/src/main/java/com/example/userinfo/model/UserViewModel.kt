package com.example.userinfo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
//    var userModel = MutableLiveData<UserModel>()

    private val _username = MutableLiveData<String>("")
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<Int>(0)
    val phone: LiveData<Int> = _phone

    private val _pincode = MutableLiveData<Int>(0)
    val pincode: LiveData<Int> = _pincode

    private val _address = MutableLiveData<String>("")
    val address: LiveData<String> = _address

    fun setUsername(username: String){
        _username.value = username
    }

    fun setEmail(email: String){
        _email.value = email
    }

    fun setPhone(phone: Int){
        _phone.value = phone
    }

    fun setPincode(pincode: Int){
        _pincode.value = pincode
    }

    fun setAddress(address: String){
        _address.value = address
    }

}