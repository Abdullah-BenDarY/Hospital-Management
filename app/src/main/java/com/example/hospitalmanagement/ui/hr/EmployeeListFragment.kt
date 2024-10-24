package com.example.hospitalmanagement.ui.hr

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.models.UsersData
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.adapters.CategoryAdapter
import com.example.hospitalmanagement.adapters.EmployeeListAdapter
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentEmployeeListBinding
import com.example.hospitalmanagement.utils.ALL
import com.example.hospitalmanagement.utils.ANALYSIS
import com.example.hospitalmanagement.utils.DOCTOR
import com.example.hospitalmanagement.utils.HR
import com.example.hospitalmanagement.utils.NURSE
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class EmployeeListFragment :
    BaseFragment<FragmentEmployeeListBinding, HrContract.ViewModel>() {
    private val hrViewModel: HrContract.ViewModel by viewModels<HrViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEmployeeListBinding.inflate(inflater, container, false)

    override fun initViewModel(): HrContract.ViewModel {
        return hrViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hrViewModel.doIntent(HrContract.Intent.getAllUsers("All"))

        observeUsers()
        initClicks()
    }

    private fun initClicks() {
        binding.btnNewEmployee.setOnClickListener {
            findNavController()
                .navigate(R.id.newUserFragment)
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeUsers() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun setupCategoryAdapter(usersData: List<UsersData>?) {
        val dataList = listOf(ALL, DOCTOR, NURSE, HR, ANALYSIS)
        val categoryRecyclerView = binding.rvCategory
        val categoryAdapter = CategoryAdapter()
        categoryAdapter.list = dataList

        // Set a listener for category clicks
        categoryAdapter.setOnCategoryClickListener { selectedCategory ->
            filterUsersByCategory(selectedCategory, usersData)
        }

        categoryRecyclerView.adapter = categoryAdapter
    }

    private fun filterUsersByCategory(category: String, usersData: List<UsersData>?) {
        val filteredList = when (category) {
            DOCTOR -> usersData?.filter { it.type == DOCTOR }
            NURSE -> usersData?.filter { it.type == NURSE }
            HR -> usersData?.filter { it.type == HR }
            ANALYSIS -> usersData?.filter { it.type == ANALYSIS }
            else -> usersData
        }
        setupUsersAdapter(filteredList)
        setupSearch(filteredList)

    }

    private fun setupUsersAdapter(usersData: List<UsersData>?) {
        val rv = binding.rvEmployee
        val employeeListAdapter = EmployeeListAdapter()
        employeeListAdapter.list = usersData

        employeeListAdapter.setOnItemClickListener { usersData ->
            showMessage("${usersData.id} + ${usersData.type}")
            findNavController()
                .navigate(EmployeeListFragmentDirections.globalActionToProfileFragment(usersData.id!!))
        }
        rv.adapter = employeeListAdapter
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

    private fun handleEvents(event: HrContract.Event) {
        when (event) {
            is HrContract.Event.InitialEvent -> {}
            is HrContract.Event.ShowData -> {
                binding.lottie.visibility = View.GONE

                event.modelAllUsers.data?.let {
                    setupSearch(it)
                    setupCategoryAdapter(it)
                    setupUsersAdapter(it)
                }
            }

            is HrContract.Event.ShowNewUserResponse -> {}
        }
    }

    private fun handleStates(state: HrContract.State?) {
        when (state) {
            is HrContract.State.ShowErrorMessage -> showMessage(state.uiMessage)
            is HrContract.State.ShowThrowableMessage -> showMessage(state.throwable.message)
            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

}