package id.interview.newsapi.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import id.interview.newsapi.R
import id.interview.newsapi.repository.IView
import id.interview.newsapi.repository.NetworkingState
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.base.BaseFragment
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