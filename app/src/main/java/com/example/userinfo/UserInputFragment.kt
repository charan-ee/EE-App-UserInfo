package com.example.userinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.userinfo.databinding.FragmentUserInputBinding
import com.example.userinfo.model.UserViewModel

class UserInputFragment : Fragment() {

    private var _fragmentUserInputBinding: FragmentUserInputBinding? = null
    private val fragmentUserInputBinding get() = _fragmentUserInputBinding!!
    private lateinit var validator: Validator

    private lateinit var userViewModel: UserViewModel
    private lateinit var displayInfoFragment: DisplayInfoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_fragmentUserInputBinding == null) {
            _fragmentUserInputBinding = FragmentUserInputBinding.inflate(inflater, container, false)
        }
        validator = Validator()
        displayInfoFragment = DisplayInfoFragment()
        userViewModel = activity?.let { ViewModelProvider(it)[UserViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")
        return fragmentUserInputBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.isValid.observe(viewLifecycleOwner) {
            userViewModel.isVisible.value?.let { it1 -> changeButtonsVisibility(it1) }
            userViewModel.isEnable.value?.let { it1 -> changeEditViews(it1) }
        }

        fragmentUserInputBinding.buttonValidate.setOnClickListener() {
            isDataValid()
        }

        fragmentUserInputBinding.buttonCancel.setOnClickListener() {
            userViewModel.setVisibility(false)
            userViewModel.isVisible.value?.let { it1 -> changeButtonsVisibility(it1) }

            userViewModel.setIsEnable(true)
            userViewModel.isEnable.value?.let { changeEditViews(it) }

        }

        fragmentUserInputBinding.buttonConfirm.setOnClickListener() {
            val displayInfoFragment = DisplayInfoFragment()
            setUserDetailsInVM(fragmentUserInputBinding)
            replaceFragment(displayInfoFragment)
        }
    }

    private fun isDataValid() {
        val isAllFieldsFilled = validator.validateAllFields(
            fragmentUserInputBinding.constraintLayout,
            requireActivity()
        )
        if (isAllFieldsFilled
            && validator.validatePhoneField(
                fragmentUserInputBinding.editTextViewPhone.text.toString(),
                requireActivity()
            )
            && validator.validatePincodeField(
                fragmentUserInputBinding.editTextViewPincode.text.toString(),
                requireActivity()
            )
            && validator.validateEmailField(
                fragmentUserInputBinding.editTextViewEmail.text.toString(),
                requireActivity()
            )
        ) {
            userViewModel.setIsValid(true)
            userViewModel.setVisibility(true)
            userViewModel.isVisible.value?.let { changeButtonsVisibility(it) }

            userViewModel.setIsEnable(false)
            userViewModel.isEnable.value?.let { changeEditViews(it) }
        }
    }

    private fun setUserDetailsInVM(fragmentUserInputBinding: FragmentUserInputBinding) {
        val binding = fragmentUserInputBinding
        userViewModel.setUserDetails(
            binding.editTextViewUserName.text.toString(),
            binding.editTextViewEmail.text.toString(),
            binding.editTextViewPhone.text.toString(),
            binding.editTextViewPincode.text.toString(),
            binding.editTextViewAddress.text.toString()
        )
    }

    private fun changeButtonsVisibility(visible: Boolean) {
        val binding = fragmentUserInputBinding
        binding.buttonValidate.isVisible = !visible
        binding.buttonLayout.isVisible = visible
    }

    private fun changeEditViews(enable: Boolean) {
        val layout = fragmentUserInputBinding.constraintLayout
        for (i in 0 until layout.childCount) {
            val v = layout.getChildAt(i)
            if (v is EditText) {
                v.isEnabled = enable
            }
        }
    }

    private fun replaceFragment(displayInfoFragment: DisplayInfoFragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.main_container, displayInfoFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }


}