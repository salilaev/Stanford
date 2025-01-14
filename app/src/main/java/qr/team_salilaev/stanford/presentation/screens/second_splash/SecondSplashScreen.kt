import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.ScreenSecondSplashBinding

@SuppressLint("CustomSplashScreen")
class SecondSplashScreen : Fragment(R.layout.screen_second_splash) {
    private val binding: ScreenSecondSplashBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loading.progress = 100f
        lifecycleScope.launch {
            delay(1600)
            findNavController().navigate(R.id.action_secondSplashScreen_to_mainScreen)
        }
    }

}