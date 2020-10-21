package id.interview.moviedb.support

import android.app.Activity
import android.app.ProgressDialog
import android.os.Build

class DialogLoading(var activity: Activity) {
    val dialog: ProgressDialog? by lazy {
        ProgressDialog(activity).apply {
            setMessage("Loading")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    fun showDialog() {
        dialog?.show()
    }

    fun dismissDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity.isDestroyed) return
        } else {
            if (activity.isFinishing) return
        }

        if (dialog != null && dialog?.isShowing!!) dialog?.dismiss()
    }
}