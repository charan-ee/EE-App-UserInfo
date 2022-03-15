package com.example.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import com.example.userinfo.databinding.FragmentUserInputBinding
import com.example.userinfo.model.UserViewModel

class UserInputFragment : Fragment() {

    private var fragmentUserInputBinding: FragmentUserInputBinding? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userInputFragment = FragmentUserInputBinding.inflate(inflater, container, false)
        fragmentUserInputBinding = userInputFragment
        return userInputFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = UserViewModel()


    }

}