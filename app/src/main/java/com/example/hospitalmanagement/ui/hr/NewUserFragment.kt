package com.example.hospitalmanagement.ui.hr

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentNewUserBinding
import com.example.hospitalmanagement.utils.shakeErrorView
import com.example.hospitalmanagement.utils.showMessage
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class NewUserFragment :
    BaseFragment<FragmentNewUserBinding, HrContract.ViewModel>() {
    private val hrViewModel: HrContract.ViewModel by viewModels<HrViewModel>()
    var date: String? = null

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewUserBinding.inflate(inflater, container, false)

    override fun initViewModel(): HrContract.ViewModel {
        return hrViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUsers()
        initClicks()
    }

    private fun observeUsers() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun handleEvents(event: HrContract.Event) {
        when (event) {
            is HrContract.Event.InitialEvent -> {}
            is HrContract.Event.ShowData -> {}

            is HrContract.Event.ShowNewUserResponse -> {
                showMessage(event.message)
            }
        }
    }

    private fun handleStates(state: HrContract.State?) {
        when (state) {
            is HrContract.State.ShowErrorMessage -> showMessage(state.uiMessage)
            is HrContract.State.ShowThrowableMessage -> showMessage(state.throwable.message)
            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun initClicks() {
        binding.txtBirthday.setOnClickListener {
            showDatePicker()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnCreateUser.setOnClickListener {
            validation()
        }
    }

    private fun showDatePicker() {

        val today = MaterialDatePicker.todayInUtcMilliseconds()

        // Create CalendarConstraints to limit the selection to past dates (including today)
        val constraints = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.before(today)) // Only allow dates before or equal to today
            .build()

        // Build the MaterialDatePicker with the constraints
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(today) // Default selection is today
            .setCalendarConstraints(constraints) // Apply the constraints
            .build()

        // Show the date picker
        datePicker.show(parentFragmentManager, "Material")

        // Set the result when a date is selected
        datePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            binding.txtBirthday.text = dateFormat.format(Date(it).time)
            date = dateFormat.format(Date(it).time)
        }
    }


    private fun validation(): Boolean {
        binding.apply {
            val fName = edtFName.text.toString().trim()
            val lName = edtLName.text.toString().trim()
            val email = edtEmailAddress.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val address = edtAddress.text.toString().trim()
            val phone = editPhone.text.toString().trim()
            val gender = spinnerGender.selectedItemPosition
            val type = spinnerSpecialist.selectedItemPosition
            val status = spinnerStatus.selectedItemPosition

            // Handle empty field validation
            when {
                fName.isEmpty() -> showFieldError(edtFName, R.string.required)
                lName.isEmpty() -> showFieldError(edtLName, R.string.required)
                gender == 0 -> showMessage(requireContext().getString(R.string.please_select_gender))
                type == 0 -> showMessage(requireContext().getString(R.string.specialist_hint))
                date == null -> showMessage(requireContext().getString(R.string.birthday_hint))
                status == 0 -> showMessage(requireContext().getString(R.string.status_hint))
                phone.isEmpty() -> showFieldError(editPhone, R.string.required)
                email.isEmpty() -> showFieldError(edtEmailAddress, R.string.required)
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> showFieldError(
                    edtEmailAddress,
                    R.string.wrong_email_address, false
                )

                address.isEmpty() -> showFieldError(edtAddress, R.string.required, false)
                password.isEmpty() -> showFieldError(editPassword, R.string.required, false)
                else -> {
                    // All validations passed, create user
                    viewModel.doIntent(
                        HrContract.Intent.createUser(
                            email,
                            password,
                            fName,
                            lName,
                            spinnerGender.selectedItem.toString(),
                            spinnerSpecialist.selectedItem.toString(),
                            date!!,
                            spinnerStatus.selectedItem.toString(),
                            address,
                            phone,
                            spinnerSpecialist.selectedItem.toString()
                        )
                    )
                    return true
                }
            }
            return false
        }
    }

    private fun showFieldError(view: EditText, errorResId: Int, scrollToView: Boolean = true) {
        if (scrollToView) {
            binding.scrollView.post {
                binding.scrollView.smoothScrollTo(0, view.top)
            }
        }
        view.error = getString(errorResId)
        shakeErrorView(view)
    }
}