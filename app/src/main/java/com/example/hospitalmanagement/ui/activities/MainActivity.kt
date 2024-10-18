package com.example.hospitalmanagement.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseActivity
import com.example.hospitalmanagement.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun inflateBinding(): ActivityMainBinding
            = ActivityMainBinding.inflate(layoutInflater)
}