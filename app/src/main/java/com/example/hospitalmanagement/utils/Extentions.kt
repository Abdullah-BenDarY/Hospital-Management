package com.example.hospitalmanagement.utils

import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showMessage(message: Any?) {
    val rootView = requireView()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Snackbar.make(rootView, message.toString(), Snackbar.LENGTH_LONG).show()
    } else {
        Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_LONG).apply {
            show()
        }
    }
}

fun shakeErrorView(view: View) {
    val shakeAnimation = ObjectAnimator.ofFloat(view, "translationX", 0f, -10f, 10f, -10f, 10f, 0f)
    shakeAnimation.duration = 300
    shakeAnimation.start() }

fun animateSplashImageView(view: View) {
    val animator = ObjectAnimator.ofFloat(view, "translationY", -100f, 0f)
    animator.duration = 3000
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.start() }