package id.interview.newsapi.support.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class FragmentAdapter(fm: FragmentManager, isShowTitle: Boolean) : FragmentPagerAdapter(fm) {

    private val mFragments = ArrayList<Fragment>()
    private val mFragmentTitles = ArrayList<String>()
    private var isShowTitle = true

    init {
        this.isShowTitle = isShowTitle
    }

    fun addFragment(fragment: Fragment, title: String = "") {
        mFragments.add(fragment)
        mFragmentTitles.add(title)
    }

    fun getItems(): List<Fragment> {
        return mFragments
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (isShowTitle) {
            mFragmentTitles[position]
        } else {
            ""
        }
    }
}
