import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.ScreenFirstSplashBinding

@SuppressLint("CustomSplashScreen")
class FirstSplashScreen : Fragment(R.layout.screen_first_splash) {
    private val binding: ScreenFirstSplashBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationAlphaWithHide(binding.first, binding.second)

        lifecycleScope.launch {
            delay(6000)
            findNavController().navigate(R.id.action_firstSplashScreen_to_secondSplashScreen)
        }
    }

    private fun animationAlphaWithHide(firstText: TextView, secondText: TextView) {
        // Анимация появления для первого текста
        ObjectAnimator.ofFloat(firstText, View.ALPHA, 0f, 1f).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            start()

            // Когда первая анимация закончится, запускаем исчезновение первого текста и появление второго
            doOnEnd {
                // Исчезновение первого текста
                firstText.animate().alpha(0f).setDuration(1000).start()

                // Появление второго текста с небольшой задержкой
                secondText.postDelayed({
                    ObjectAnimator.ofFloat(secondText, View.ALPHA, 0f, 1f).apply {
                        duration = 2000
                        interpolator = AccelerateDecelerateInterpolator()
                        start()
                    }
                }, 1000) // Задержка для появления второго текста после исчезновения первого
            }
        }
    }
}