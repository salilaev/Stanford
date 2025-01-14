package qr.team_salilaev.stanford.presentation.screens.main

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.ScreenMainBinding

@SuppressLint("CustomMainScreen")
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding: ScreenMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationFromBottom(binding.buttonCourses, binding.buttonGroup, binding.buttonTeachers, binding.picture)
        clickButtons()
    }

    private fun clickButtons() {
        binding.buttonCourses.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_coursesScreen)
        }
        binding.buttonGroup.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_groupsScreen)
        }
        binding.buttonTeachers.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreen_to_teacherScreen)
    }
}


private fun animationFromBottom(first: View, second: View, third: View, stanfordPicture: View) {
    ObjectAnimator.ofFloat(first, "translationY", 700f, 0f).apply {
        duration = 700
        interpolator = AccelerateDecelerateInterpolator()
        start()
    }

    ObjectAnimator.ofFloat(second, "translationY", 700f, 0f).apply {
        duration = 700
        interpolator = AccelerateDecelerateInterpolator()
        start()
    }

    ObjectAnimator.ofFloat(third, "translationY", 700f, 0f).apply {
        duration = 700
        interpolator = AccelerateDecelerateInterpolator()
        start()
    }

    ObjectAnimator.ofFloat(stanfordPicture, "translationY", -700f, 0f).apply {
        duration = 700
        interpolator = AccelerateDecelerateInterpolator()
        start()
    }
}
}