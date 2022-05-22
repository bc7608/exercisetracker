package org.tensorflow.lite.examples.poseestimation

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType

class IntroActivity : AppIntro() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getPreferences(MODE_PRIVATE)
        val firstTime = sharedPref.getBoolean(getString(R.string.first_time_opened), true)

        if (firstTime){
            addSlide(AppIntroFragment.createInstance(
                title = "Welcome to ExerciseCounter",
                description = "Track your exercises..." ,
                imageDrawable = R.drawable.ic_muscle,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.primaryColor,
                titleTypefaceFontRes = R.font.montserrat_bold,
                descriptionTypefaceFontRes = R.font.montserrat_black
            ))

            addSlide(AppIntroFragment.createInstance(
                title = "Rep counter",
                description = "...and count your reps" ,
                imageDrawable = R.drawable.ic_push_up,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.primaryColor,
                titleTypefaceFontRes = R.font.montserrat_bold,
                descriptionTypefaceFontRes = R.font.montserrat_black
            ))

            // Ask for required CAMERA permission on the second slide.
            askForPermissions(
                permissions = arrayOf(Manifest.permission.CAMERA),
                slideNumber = 2,
                required = true)


            isColorTransitionsEnabled = true
            setTransformer(AppIntroPageTransformerType.Zoom)
            isIndicatorEnabled = true

            with (sharedPref.edit()) {
                putBoolean(getString(R.string.first_time_opened), false)
                apply()
            }

        }
        else{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}