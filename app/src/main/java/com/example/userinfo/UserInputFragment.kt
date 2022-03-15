package com.example.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.lifecycle.ViewModelProvider
import com.example.userinfo.databinding.FragmentUserInputBinding
import com.example.userinfo.model.UserViewModel

class UserInputFragment : Fragment() {

    private var fragmentUserInputBinding: FragmentUserInputBinding? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var displayInfoFragment: DisplayInfoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserInputBinding = FragmentUserInputBinding.inflate(inflater, container, false)
//        fragmentUserInputBinding = userInputFragment
        return fragmentUserInputBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayInfoFragment = DisplayInfoFragment()

        userViewModel = activity?.let { ViewModelProvider(it)[UserViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")



    }

}