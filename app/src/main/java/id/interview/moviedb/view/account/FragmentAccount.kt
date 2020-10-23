package id.interview.moviedb.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import id.interview.moviedb.R
import id.interview.moviedb.repository.IView
import id.interview.moviedb.repository.NetworkingState
import id.interview.moviedb.repository.ViewNetworkState
import id.interview.moviedb.repository.base.BaseFragment
import id.interview.moviedb.support.showActivityWithClearTop
import id.interview.moviedb.view.Root
import kotlinx.android.synthetic.main.include_settings_content.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentAccount : BaseFragment(),ViewNetworkState,IView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
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
        login_logout?.setOnClickListener {
            showConfirmLogout()
        }
    }
    private fun showConfirmLogout() {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setMessage("Apakah Anda yakin ingin keluar?")
                setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    activity?.finish()
                }
                setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
            }.show()
        }
    }
}