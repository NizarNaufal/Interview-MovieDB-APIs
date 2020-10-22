package id.interview.moviedb.view.listcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.interview.moviedb.R
import id.interview.moviedb.repository.IView
import id.interview.moviedb.repository.NetworkingState
import id.interview.moviedb.repository.ViewNetworkState
import id.interview.moviedb.repository.base.BaseFragment
import id.interview.moviedb.support.*
import id.interview.moviedb.view.home.modules.MoviesModels
import id.interview.moviedb.view.listcategory.business.BusinessActivity
import id.interview.moviedb.view.listcategory.entertainment.EntertainmentActivity
import id.interview.moviedb.view.listcategory.general.GeneralActivity
import id.interview.moviedb.view.listcategory.health.HealthActivity
import id.interview.moviedb.view.listcategory.science.ScienceActivity
import id.interview.moviedb.view.listcategory.sports.SportsActivity
import id.interview.moviedb.view.listcategory.technology.TechnologyActivity
import kotlinx.android.synthetic.main.fragment_movies.*

class FragmentCategory : BaseFragment(), ViewNetworkState, IView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
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
