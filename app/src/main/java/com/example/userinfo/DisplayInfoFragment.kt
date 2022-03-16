package com.example.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.userinfo.databinding.FragmentDisplayInfoBinding
import com.example.userinfo.model.UserViewModel
import java.lang.String.format

class DisplayInfoFragment : Fragment() {

    private var _displayInfoBinding: FragmentDisplayInfoBinding? = null
    private val displayInfoBinding get() = _displayInfoBinding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _displayInfoBinding = FragmentDisplayInfoBinding.inflate(inflater, container, false)
        userViewModel = activity?.let { ViewModelProvider(it)[UserViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")
        return displayInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = userViewModel.username.value
        val email = userViewModel.email.value
        val address = userViewModel.address.value
        val phone = userViewModel.phone.value
        val pincode = userViewModel.pincode.value

        val userInfoText = format(getString(R.string.userinfo_text), username, address, pincode, phone, email)

        displayInfoBinding.detailsTV.text = userInfoText
    }
}