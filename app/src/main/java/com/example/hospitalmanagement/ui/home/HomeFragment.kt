package com.example.hospitalmanagement.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.base.BaseViewModel
import com.example.hospitalmanagement.databinding.FragmentHomeBinding
import com.example.hospitalmanagement.utils.ANALYSIS
import com.example.hospitalmanagement.utils.DOCTOR
import com.example.hospitalmanagement.utils.HR
import com.example.hospitalmanagement.utils.MANAGER
import com.example.hospitalmanagement.utils.NURSE
import com.example.hospitalmanagement.utils.RECEPTIONIST
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {

    private val loginViewModel: BaseViewModel by viewModels()
    private val args by navArgs<HomeFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initViewModel(): BaseViewModel {
        return loginViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initClicks()
    }

    private fun initUi() {
        binding.apply {
            ("${args.modelLogin.data?.firstName} " + "${args.modelLogin.data?.lastName}").also {
                tvUserName.text = it
            }
            tvUserDetails.text = args.modelLogin.data?.specialist
        }

        if (args.modelLogin.data?.type.equals(RECEPTIONIST)) {
            setReceptionistUi()
            setReceptionistClicks()
        } else if (args.modelLogin.data?.type.equals(DOCTOR)) {
            setDoctorUi()
            setDoctorClicks()
        } else if (args.modelLogin.data?.type.equals(NURSE)) {
            setNurseUi()
            setNurseClicks()
        } else if (args.modelLogin.data?.type.equals(MANAGER)) {
            setManagerUi()
            setManagerClicks()
        } else if (args.modelLogin.data?.type.equals(ANALYSIS)) {
            setAnalysisUi()
            setAnalysisClicks()
        } else if (args.modelLogin.data?.type.equals(HR)) {
            setHrClicks()
        }
    }

    private fun setReceptionistUi() {
        binding.apply {
            btnFirst.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_user)
            btnFirst.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
            btnFirst.text = ContextCompat.getString(requireContext(), R.string.calls)
        }
    }

    private fun setReceptionistClicks() {
        binding.apply {
            btnFirst
        }
    }

    private fun setAnalysisUi() {
        binding.apply {
            btnFirst.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_sheild)
            btnFirst.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
            btnFirst.text = ContextCompat.getString(requireContext(), R.string.cases)
        }
    }

    private fun setAnalysisClicks() {
        binding.apply {
            btnFirst
        }
    }

    private fun setDoctorUi() {
        binding.apply {
            btnFirst.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_user)
            btnFirst.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
            btnFirst.text = ContextCompat.getString(requireContext(), R.string.calls)
            //\\\\\\\\\\//
            btnLast.visibility = VISIBLE
            btnLast.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_sheild)
            btnLast.text = ContextCompat.getString(requireContext(), R.string.cases)
        }
    }

    private fun setDoctorClicks() {
        binding.apply {
            btnFirst.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCallsFragment())
            }
            btnLast.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDoctorCasesFragment())
            }
        }
    }

    private fun setNurseUi() {
        binding.apply {
            btnFirst.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_user)
            btnFirst.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
            btnFirst.text = ContextCompat.getString(requireContext(), R.string.calls)
            //\\\\\\\\\\//
            btnLast.visibility = VISIBLE
            btnLast.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_sheild)
            btnLast.text = ContextCompat.getString(requireContext(), R.string.cases)
        }
    }

    private fun setNurseClicks() {
        binding.apply {
            btnFirst.setOnClickListener { showMessage("btnFirst") }
            btnLast.setOnClickListener { showMessage("btnLast") }
        }
    }

    private fun setManagerUi() {
        binding.apply {
            btnFirst.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_sheild)
            btnFirst.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue))
            btnFirst.text = ContextCompat.getString(requireContext(), R.string.cases)
            //\\\\\\\\\\//
            btnLast.visibility = VISIBLE
        }
    }

    private fun setManagerClicks() {
        binding.apply {
            btnFirst.setOnClickListener { showMessage("btnFirst") }
            btnLast.setOnClickListener { showMessage("btnLast") }
        }
    }

    private fun setHrClicks() {
        binding.apply {
            btnFirst.setOnClickListener {
                findNavController().navigate(
                    R.id.global_action_to_employeeListFragment
                )
            }
        }
    }

    private fun initClicks() {
        binding.apply {
            imgUser.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections
                        .globalActionToProfileFragment(args.modelLogin.data?.id!!)
                )
            }
            btnTasks.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections
                        .actionHomeFragmentToTasksFragment()
                )
            }
            btnReports.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections
                        .actionHomeFragmentToReportsFragment()
                )
                btnFingerPrint.setOnClickListener {
                    showMessage("btnFingerPrint")
//                    findNavController().navigate(
//                        HomeFragmentDirections
//                            .actionHomeFragmentToFingerPrintFragment())
                }

            }
        }
    }

}
