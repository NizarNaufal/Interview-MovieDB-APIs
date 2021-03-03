package id.interview.newsapi.view.listcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.interview.newsapi.R
import id.interview.newsapi.repository.IView
import id.interview.newsapi.repository.NetworkingState
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.base.BaseFragment
import id.interview.newsapi.support.*
import id.interview.newsapi.view.listcategory.business.BusinessActivity
import id.interview.newsapi.view.listcategory.entertainment.EntertainmentActivity
import id.interview.newsapi.view.listcategory.general.GeneralActivity
import id.interview.newsapi.view.listcategory.health.HealthActivity
import id.interview.newsapi.view.listcategory.science.ScienceActivity
import id.interview.newsapi.view.listcategory.sports.SportsActivity
import id.interview.newsapi.view.listcategory.technology.TechnologyActivity
import kotlinx.android.synthetic.main.fragment_news.*

class FragmentCategory : BaseFragment(), ViewNetworkState, IView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }
    override fun onDestroy() {
        super.onDestroy()
        networkState = NetworkingState.Destroy()
    }

    override fun initView() {
        lyt_parent_one?.setOnClickListener {
            activity?.showActivity(BusinessActivity::class.java)
        }
        lyt_parent_two?.setOnClickListener {
            activity?.showActivity(EntertainmentActivity::class.java)
        }
        lyt_parent_four?.setOnClickListener {
            activity?.showActivity(GeneralActivity::class.java)
        }
        lyt_parent_five?.setOnClickListener {
            activity?.showActivity(HealthActivity::class.java)
        }
        lyt_parent_six?.setOnClickListener {
            activity?.showActivity(ScienceActivity::class.java)
        }
        lyt_parent_seven?.setOnClickListener {
            activity?.showActivity(SportsActivity::class.java)
        }
        lyt_parent_eight?.setOnClickListener {
            activity?.showActivity(TechnologyActivity::class.java)
        }
    }

}
