package com.example.hospitalmanagement.ui.doctor

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.UsersData
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.adapters.doctor.AdapterNursesList
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentAddNurseBinding
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorContract
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorViewModel
import com.example.hospitalmanagement.utils.SharedPrefs
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class AddNurseFragment : BaseFragment<FragmentAddNurseBinding, DoctorContract.ViewModel>() {

    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private val adapterNursesList = AdapterNursesList()
    private val args: AddNurseFragmentArgs by navArgs()
    private var nurseId: Int? = null


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddNurseBinding = FragmentAddNurseBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel = doctorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorViewModel.doIntent(DoctorContract.Intent.GetNurseList)
        initClickListeners()
        observe()
    }

    private fun initClickListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnAddNurse.setOnClickListener {
             invokeAddNurse(nurseId)
            }
        }
        adapterNursesList.setOnClick {
            nurseId = it
            SharedPrefs.setNurseId(it)
        }
    }

    private fun observe() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun handleEvents(event: DoctorContract.Event) {
        when (event) {
            DoctorContract.Event.InitialEvent -> {}
            is DoctorContract.Event.ShowNurseList -> {
                setupUsersAdapter(event.modelAllUsers.data)
                setupSearch(event.modelAllUsers.data)
            }
            is DoctorContract.Event.AddNurse ->{
                onAddNurseClickActions()
                setNavigateUp()
            }
        }
    }

    private fun setNavigateUp() {
        Handler(Looper.getMainLooper()).postDelayed({
           findNavController().navigateUp()}
            , 1500)
    }

    private fun handleStates(state: DoctorContract.State?) {
        when (state) {
            is DoctorContract.State.ShowErrorMessage -> showMessage(state.uiMessage)
            is DoctorContract.State.ShowThrowableMessage -> showMessage(state.throwable.message)
            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun setupUsersAdapter(data: List<UsersData>?) {
        adapterNursesList.submitList(data)
        binding.rvNurses.adapter = adapterNursesList
    }

    private fun invokeAddNurse(nurseId : Int?) {
        if (nurseId == null) {
            showMessage(getString(R.string.select_nurse))
        } else {
            doctorViewModel.doIntent(
                DoctorContract.Intent.SetNurse(
                    callId = args.caseId,
                    userId = nurseId
                )
            )
        }
    }

    private fun onAddNurseClickActions() {
        binding.apply {
            btnAddNurse.isEnabled = false
            btnAddNurse.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            btnAddNurse.text = getString(R.string.nurse_aded)
            btnAddNurse.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_header))
        }
    }

    private fun setupSearch(usersData: List<UsersData>?) {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().lowercase(Locale.getDefault())
                val filteredList =
                    usersData?.filter { it.first_name?.lowercase()?.contains(query) == true }
                setupUsersAdapter(filteredList)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}