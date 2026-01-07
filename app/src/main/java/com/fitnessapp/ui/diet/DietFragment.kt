package com.fitnessapp.ui.diet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fitnessapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DietFragment : Fragment(R.layout.fragment_diet) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        // Setup ViewPager with adapter
        val pagerAdapter = DietPagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter

        // Connect TabLayout with ViewPager
        val tabTitles = arrayOf("Recipes", "Calculator", "Tips")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private inner class DietPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> RecipesTabFragment()
                1 -> CalculatorTabFragment()
                2 -> TipsTabFragment()
                else -> RecipesTabFragment()
            }
        }
    }
}
