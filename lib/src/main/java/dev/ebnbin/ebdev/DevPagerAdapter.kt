package dev.ebnbin.ebdev

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.ebnbin.eb.app

internal class DevPagerAdapter(
    fragment: Fragment,
    private val devPageList: List<DevPage>,
) : FragmentStateAdapter(fragment) {
    private val fragmentManager: FragmentManager = fragment.childFragmentManager

    override fun getItemCount(): Int {
        return devPageList.size
    }

    override fun createFragment(position: Int): Fragment {
        val devPage = devPageList[position]
        return fragmentManager.fragmentFactory.instantiate(app.classLoader, devPage.fragmentClassName).also {
            it.arguments = devPage.fragmentArgs
        }
    }
}
