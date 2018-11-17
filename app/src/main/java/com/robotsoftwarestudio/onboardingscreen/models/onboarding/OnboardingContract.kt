package com.robotsoftwarestudio.onboardingscreen.models.onboarding

import android.content.SharedPreferences

interface OnboardingContract {

    interface View {

        fun dataOnboarding(listOnboarding: List<Onboarding>)
        fun dataOfSlide(i: Int): Int
        fun dataToPreference(sharedPref: SharedPreferences)

    }

    interface Presenter {

        fun getDataOnBoarding()
        fun onClickNext(view: android.view.View)
        fun onClickPrevious(view: android.view.View)
        fun saveDataToPreference()

    }

}