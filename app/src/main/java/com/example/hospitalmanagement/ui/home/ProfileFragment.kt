package com.example.hospitalmanagement.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.models.ModelLogin
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.utils.showToast


class ProfileFragment : Fragment() {
    private var modelLogin: ModelLogin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arg ->
            modelLogin = arguments?.getParcelable("modelLogin")


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    fun makeCallBack() {
        if(modelLogin!=null){
            showToast(modelLogin?.data?.firstName.toString())
        }
    }
}