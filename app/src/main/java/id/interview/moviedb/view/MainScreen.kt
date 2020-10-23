package id.interview.moviedb.view

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import id.interview.moviedb.R
import id.interview.moviedb.view.home.FragmentHome
import id.interview.moviedb.view.account.FragmentAccount
import id.interview.moviedb.view.listcategory.FragmentCategory

enum class MainScreen(@IdRes val menuItemId: Int, val fragment: Fragment) {
    TRENDING(R.id.first_fragment, FragmentHome()),
    CATEGORY(R.id.second_fragment, FragmentCategory()),
    ACCOUNT(R.id.fourth_fragment, FragmentAccount()),
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}