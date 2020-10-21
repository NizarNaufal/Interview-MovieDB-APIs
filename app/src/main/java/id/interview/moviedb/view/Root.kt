package id.interview.moviedb.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.greenfrvr.rubberloader.RubberLoaderView
import com.sembozdemir.permissionskt.askPermissions
import com.sembozdemir.permissionskt.handlePermissionsResult
import id.interview.moviedb.R
import id.interview.moviedb.repository.IView
import id.interview.moviedb.repository.NetworkingState
import id.interview.moviedb.repository.ViewNetworkState
import id.interview.moviedb.repository.base.BaseActivity
import id.interview.moviedb.support.showActivityWithClearTop
import id.interview.moviedb.support.showLog
import kotlinx.android.synthetic.main.activity_splash.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class Root : BaseActivity(), ViewNetworkState, IView {

//    private val presenter by lazy { ProfilePresenter(baseContext, this) }
//    private val session by lazy { AppSession(baseContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        (findViewById<android.view.View?>(R.id.loader1) as RubberLoaderView?)?.startLoading()
        var myTimer = Timer()
        myTimer.schedule(
            object : TimerTask() {
                override fun run() {
                    // If you want to modify a view in your Activity
                    this@Root.runOnUiThread { showNextActivity() }
                }
            }, 3000)
        askPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) {
            onGranted {
                initView()
            }

            onDenied {
                it.forEach {
                    when (it) {
                        Manifest.permission.CAMERA -> Manifest.permission.WRITE_EXTERNAL_STORAGE
                        Manifest.permission.ACCESS_FINE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
                    }
                }
            }

            onShowRationale { request ->

                var permissions = ""
                request.permissions.forEach {

                    permissions += when (it) {
                        Manifest.permission.CAMERA ->
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        Manifest.permission.ACCESS_FINE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
                        else -> ""
                    }

                }

                snack("You should grant permission for $permissions") {
                    request.retry()
                }
            }

            onNeverAskAgain {
                it.forEach {
                    when (it) {
                        Manifest.permission.CAMERA ->
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        Manifest.permission.ACCESS_FINE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION

                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        handlePermissionsResult(requestCode, permissions, grantResults)
    }

    private fun toast(messsage: String) {
        Toast.makeText(this, messsage, Toast.LENGTH_LONG).show()
    }

    private fun snack(message: String, action: () -> Unit = {}) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry", { _ -> action() })
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkState = NetworkingState.Destroy()
    }

    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
        runOnUiThread { showNextActivity() }
    }

    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
        runOnUiThread {
            when (message.toString()) {
//                FAILED_LOGOUT -> {
//                    if (session.isLogin()) showToast(message.toString())
//
//                    session.clearSession()
//                    showActivityWithClearTop(LoginActivity::class.java)
//                }
//                else -> showMessageDialog(message.toString())
            }
        }
    }

    override fun initView() {
//        val accessToken = AccessToken.getCurrentAccessToken()
//        val isLoggedIn = accessToken != null && !accessToken.isExpired
//
//        showLog("facebook accessToken : ${accessToken?.token}")
//        showLog("facebook status login : ${if (isLoggedIn) "Login" else "Belum Login"}")

//        requestGetProfile()
    }
//
//    private fun requestGetProfile() {
//        presenter.getProfile()
//    }

    private fun showNextActivity() {
        Handler().postDelayed({
                showActivityWithClearTop(HomeActivity::class.java)
        }, 1500)
    }

    private fun showMessageDialog(message: String?) {
        AlertDialog.Builder(this).apply {
            setTitle("Mohon Maaf")
            setMessage(message)
            setPositiveButton("Ulangi") { dialog, _ ->
                dialog?.dismiss()
//                requestGetProfile()
            }
            setNegativeButton("Tutup") { dialog, _ ->
                dialog?.dismiss()
                finish()
            }
            setOnCancelListener {
                it.dismiss()
                finish()
            }
        }.show()
    }

    private fun printKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo("id.enesis.enesmart", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                showLog("KeyHas : ${Base64.encodeToString(md.digest(), Base64.DEFAULT)}")
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        val sha1 = byteArrayOf(
            0x25,
            0xCE.toByte(),
            0x29.toByte(),
            0x27,
            0xB9.toByte(),
            0x27,
            0xE6.toByte(),
            0xFB.toByte(),
            0x74,
            0x20,
            0xE3.toByte(),
            0xED.toByte(),
            0xE7.toByte(),
            0x56.toByte(),
            0xD4.toByte(),
            0x83.toByte(),
            0x63,
            0x59.toByte(),
            0x94.toByte(),
            0x64.toByte()
        )
        showLog("keyhash : ${Base64.encodeToString(sha1, Base64.NO_WRAP)}")
    }

}
