package com.example.hospitalmanagement.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.hospitalmanagement.R


abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private var _binding: VB? = null
    open val binding get() = _binding!!
    private var progressDialog: ProgressDialog? = null
    lateinit var viewModel: VM

    protected var mContext: Context? = null
    protected var mActivity: Activity? = null
    protected var userToken: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

        if (context is Activity) {
            mActivity = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
        mActivity = null
    }

    fun navigateTo(view: View, id: Int, bundle: Bundle?) {
        try {
            Navigation.findNavController(view).navigate(id, bundle)
        } catch (e: IllegalArgumentException) {
            // Handle exception if necessary
        }
    }

    fun navigateTo(view: View, id: Int) {
        Navigation.findNavController(view).navigate(id)
    }

    fun navigateWithHostTo(id: Int, bundle: Bundle?) {
        try {
            mActivity?.let {
                Navigation.findNavController(it, R.id.fragmentContainerView)
                    .navigate(id, bundle)
            }
        } catch (e: IllegalArgumentException) {
            // Handle exception if necessary
        }
    }

    fun navigateWithHostTo(id: Int) {
        mActivity?.let {
            Navigation.findNavController(it, R.id.fragmentContainerView)
                .navigate(id, null)
        }
    }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun initViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}