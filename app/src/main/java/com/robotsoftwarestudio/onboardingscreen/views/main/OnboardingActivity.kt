package com.robotsoftwarestudio.onboardingscreen.views.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.robotsoftwarestudio.onboardingscreen.R
import com.robotsoftwarestudio.onboardingscreen.extensions.gone
import com.robotsoftwarestudio.onboardingscreen.extensions.visible
import com.robotsoftwarestudio.onboardingscreen.models.onboarding.Onboarding
import com.robotsoftwarestudio.onboardingscreen.models.onboarding.OnboardingContract
import com.robotsoftwarestudio.onboardingscreen.presenters.OnboardingPresenter
import com.robotsoftwarestudio.onboardingscreen.views.adapter.OnboardingAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity: AppCompatActivity(), OnboardingContract.View {

    private lateinit var sharedPref: SharedPreferences

    private val mSlideCount = 4
    private val mHasShow = "OnboardingHasShow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        //gone btn previous
        previousBtn.gone()

        // instance presenter
        val presenter = OnboardingPresenter(this, this@OnboardingActivity)
        presenter.getDataOnBoarding()

        // action for btn next slide onboarding to next
        nextBtn.setOnClickListener { presenter.onClickNext(it) }

        // action for btn previous slide onboarding to previous
        previousBtn.setOnClickListener { presenter.onClickPrevious(it) }

        presenter.saveDataToPreference()

        // call method class
        onboardingIndicatorView()

    }

    override fun dataOnboarding(listOnboarding: List<Onboarding>) {
        // instance adapter
        onboardingSlider.adapter = OnboardingAdapter(this@OnboardingActivity, listOnboarding)
    }

    override fun dataOfSlide(i: Int): Int {

        // set item slider to show
        val current = onboardingSlider.currentItem + i

        // count slide and setup current item to show
        if(i < mSlideCount) {
            onboardingSlider.setCurrentItem(current)
        }

        return current

    }

    override fun dataToPreference(sharedPref: SharedPreferences) {

        // initialize
        this.sharedPref = sharedPref

        // check data has have or not from cache
        if(!this.sharedPref.getBoolean(mHasShow, true)) {
            // then intent
            startActivity(Intent(this@OnboardingActivity, LoadingActivity::class.java))
            finish()
        }

    }

    private fun onboardingIndicatorView() {

        // set the indicator size
        onboardingIndicator.setPageIndicators(mSlideCount)
        onboardingSlider.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {

                // set item indicator position to show
                onboardingIndicator.setCurrentPage(position)

                if(position == 0) previousBtn.gone() else previousBtn.visible()

                if(position == mSlideCount - 1) {

                    nextBtn.text = getString(R.string.Selesai)

                    nextBtn.setOnClickListener {
                        startActivity(Intent(this@OnboardingActivity, LoadingActivity::class.java))
                        finish()
                    }

                    // save to shared preference if btn finish is clicked
                    val editor = sharedPref.edit()
                    editor.putBoolean(mHasShow, false)
                    editor.apply()

                } else {
                    nextBtn.text = resources.getString(R.string.Selanjutnya)
                }
            }

        })
    }

}