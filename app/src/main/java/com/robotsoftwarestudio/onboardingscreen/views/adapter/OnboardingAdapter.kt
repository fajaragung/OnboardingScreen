package com.robotsoftwarestudio.onboardingscreen.views.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.robotsoftwarestudio.onboardingscreen.R
import com.robotsoftwarestudio.onboardingscreen.models.onboarding.Onboarding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_onboarding.view.*

class OnboardingAdapter(private val context: Context, private val listOnboarding: List<Onboarding>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
            view == `object` as RelativeLayout

    override fun getCount(): Int =
            listOnboarding.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        // inflate layout adapter
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.adapter_onboarding, container, false)

        updateUI(view, listOnboarding[position])
        container.addView(view)

        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { container.removeView(`object` as RelativeLayout) }

    // this method update data on UI
    private fun updateUI(view: View, onboarding: Onboarding) {

        // set data to UI
        view.onboardingHeader.text = onboarding.onboardingHeader
        Picasso.get().load(onboarding.onboardingImage).into(view.onboardingImage)
        view.onboardingDesc.text = onboarding.onboardingDesc

    }

}