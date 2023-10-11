package com.example.webmotorhomeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.webmotorhomeapp.databinding.FragmentBaseTabBinding
import com.example.webmotorhomeapp.ui.buy.BuyFragment
import com.example.webmotorhomeapp.ui.rent.RentFragment
import com.example.webmotorhomeapp.ui.sell.SellFragment
import com.example.webmotorhomeapp.ui.toRent.ToRentFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

abstract class BaseTabFragment: Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var pageAdapter: FragmentStateAdapter
    abstract var bindingBase: FragmentBaseTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingBase = FragmentBaseTabBinding.inflate(inflater, container, false)

        tabLayout = bindingBase.tabLayout
        viewPager = bindingBase.viewPager

        val frags = listOf<Fragment>(SellFragment(), BuyFragment(), RentFragment(), ToRentFragment())
        val adapter = MyTabPagerAdapter(this.requireActivity(), frags)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tabLayout.addTab(tab, pos)

            val tag = tab.tag.toString()
            when {
                tag.contains("sell") -> tab.text = "Vender"
                tag.contains("buy") -> tab.text = "Comprar"
                tag.contains("toRent") -> tab.text = "Alugar"
                tag.contains("rent") -> tab.text = "Alugar"
                else -> tab.text = ""
            }

        }.attach()

        return bindingBase.root
    }

    inner class MyTabPagerAdapter(fragmentActivity: FragmentActivity, private val fragments: List<Fragment>) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

//    abstract fun addTab(frag: Fragment): TabLayout.Tab?
//
//    fun setTabLayout(tabL: TabLayout) {
//        tabLayout = tabL
//    }
//    fun setViewPager(viewP: ViewPager2) {
//        viewPager = viewP
//    }
//    fun setPagerAdapter(pageAdapter: FragmentStateAdapter) {
//        viewPager.adapter = pageAdapter
//    }
}