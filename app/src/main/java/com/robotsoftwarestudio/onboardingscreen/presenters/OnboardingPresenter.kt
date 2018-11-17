package com.robotsoftwarestudio.onboardingscreen.presenters

import android.content.Context
import com.robotsoftwarestudio.onboardingscreen.R
import com.robotsoftwarestudio.onboardingscreen.models.onboarding.Onboarding
import com.robotsoftwarestudio.onboardingscreen.models.onboarding.OnboardingContract

class OnboardingPresenter(private val view: OnboardingContract.View, private val context: Context): OnboardingContract.Presenter {

    private val onboardings = mutableListOf<Onboarding>()

    override fun getDataOnBoarding() {

        // get array from resources value
        val onboardingHeader = context.resources.getStringArray(R.array.onboardingHeader)
        val onboardingImage = context.resources.obtainTypedArray(R.array.onboardingImage)
        val onboardingDesc = context.resources.getStringArray(R.array.onboardingDesc)

        onboardings.clear()

        // loop and set data to model
        for(i in onboardingHeader.indices) {
            onboardings.add(Onboarding(
                    onboardingHeader[i],
                    onboardingImage.getResourceId(i,0),
                    onboardingDesc[i]))
        }

        onboardingImage.recycle()

        // add data of model to view contract
        view.dataOnboarding(onboardings)

    }

    override fun onClickNext(view: android.view.View) {
        this.view.dataOfSlide(+1)
    }

    override fun onClickPrevious(view: android.view.View) {
        this.view.dataOfSlide(-1)
    }

    override fun saveDataToPreference() {
        val sharedPref = context.getSharedPreferences("OnboardingIntro", Context.MODE_PRIVATE)
        view.dataToPreference(sharedPref)
    }

}