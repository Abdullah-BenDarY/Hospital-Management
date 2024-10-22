package com.example.hospitalmanagement.ui.hr.employeeList

import android.os.Bundle
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
import com.example.hospitalmanagement.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeeListFragment :
    BaseFragment<FragmentEmployeeListBinding, EmployeeListContract.ViewModel>() {
    private val employeeListViewModel: EmployeeListContract.ViewModel by viewModels<EmployeeListViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEmployeeListBinding.inflate(inflater, container, false)

    override fun initViewModel(): EmployeeListContract.ViewModel {
        return employeeListViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        employeeListViewModel.doIntent(EmployeeListContract.Intent.getAllUsers("All"))

        observeUsers()
        initClicks()
    }

    private fun initClicks() {
        binding.btnNewEmployee.setOnClickListener {
//            findNavController()
//                .navigate(R.id.action_employeeListFragment_to_newUserFragment)
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
    }

    private fun setupUsersAdapter(usersData: List<UsersData>?) {
        val rv = binding.rvEmployee
        val employeeListAdapter = EmployeeListAdapter()
        employeeListAdapter.list = usersData
        employeeListAdapter.setOnItemClickListener {
            showToast("${it.id} + ${it.type}")
            // findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToProfileFragment(it.id))
        }
        rv.adapter = employeeListAdapter
    }

    private fun handleEvents(event: EmployeeListContract.Event) {
        when (event) {
            is EmployeeListContract.Event.InitialEvent -> {}
            is EmployeeListContract.Event.ShowData -> {
                binding.lottie.visibility = View.GONE
                setupCategoryAdapter(event.modelAllUsers.data)
                setupUsersAdapter(event.modelAllUsers.data)
            }
        }
    }

    private fun handleStates(state: EmployeeListContract.State?) {
        when (state) {
            is EmployeeListContract.State.ShowErrorMessage -> showToast(state.uiMessage)
            is EmployeeListContract.State.ShowThrowableMessage -> showToast(state.throwable.message)
            null -> showToast(getString(R.string.something_went_wrong))
        }
    }

}