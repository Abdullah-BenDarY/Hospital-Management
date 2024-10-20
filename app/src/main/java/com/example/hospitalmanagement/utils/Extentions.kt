package com.example.hospitalmanagement.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast (massage : Any?) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}

fun shakeErrorView(view: View) {
    val shakeAnimation = ObjectAnimator.ofFloat(view, "translationX", 0f, -10f, 10f, -10f, 10f, 0f)
    shakeAnimation.duration = 800
    shakeAnimation.start()
}


fun animateSplashImageView(view: View) {
    val animator = ObjectAnimator.ofFloat(view, "translationY", -100f, 0f)
    animator.duration = 2500
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.start()
}