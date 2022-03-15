package com.example.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.userinfo.databinding.FragmentDisplayInfoBinding
import com.example.userinfo.model.UserViewModel

class DisplayInfoFragment : Fragment() {

    private var displayInfoBinding: FragmentDisplayInfoBinding? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        displayInfoBinding = FragmentDisplayInfoBinding.inflate(inflater, container, true)
        return displayInfoBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = activity?.let { ViewModelProvider(it)[UserViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")
    }
}