package id.interview.moviedb.view

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import id.interview.moviedb.R
import id.interview.moviedb.view.home.FragmentHome
import id.interview.moviedb.view.livetv.FragmentLive
import id.interview.moviedb.view.listcategory.FragmentCategory
import id.interview.moviedb.view.series.FragmentSeries

enum class MainScreen(@IdRes val menuItemId: Int, val fragment: Fragment) {
    HOME(R.id.first_fragment, FragmentHome()),
    SHOP(R.id.second_fragment,
        FragmentCategory()
    ),
    DEALS(R.id.third_fragment, FragmentSeries()),
    NEARBY(R.id.fourth_fragment, FragmentLive()),
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}