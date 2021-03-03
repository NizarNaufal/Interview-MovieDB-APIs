package id.interview.newsapi.support

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import id.interview.newsapi.BuildConfig
import id.interview.newsapi.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val baseUrl: String
    get() {
        return BuildConfig.BASE_URL_RELEASE
    }


val tokenUrl: String
    get() {
        return BuildConfig.NEWS_API_TOKEN
    }

fun showLog(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e("TAG_MOVIES", message)
    }
}
fun DateFormat(oldstringDate: String?): String? {
    val newDate: String
    val dateFormat =
        SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
    newDate = try {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldstringDate.toString()
    }
    return newDate
}

fun getCountry(): String? {
    val locale = Locale.getDefault()
    val country = java.lang.String.valueOf(locale.country)
    return country.toLowerCase()
}

fun <C> Activity.showActivityWithClearTop(classDestination: Class<C>) {
    startActivity(Intent(this, classDestination).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
    })
}
fun parseTimeUTC(input: String?, format: String, formatResult: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val dateFormatResult = SimpleDateFormat(formatResult, Locale("ID"))
    var d = Date()

    try {
        d = dateFormat.parse(input)

    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return dateFormatResult.format(d)
}

fun <C> Activity.showActivityWithClearTop(
    classDestination: Class<C>,
    param: String,
    value: Boolean
) {
    startActivity(Intent(this, classDestination).apply {
        putExtra(param, value)
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
    })
}

fun <C> Activity.showActivity(classDestination: Class<C>, isFinish: Boolean = false) {
    startActivity(Intent(this, classDestination))
    if (isFinish) {
        finish()
    }
}

fun <C> Activity.showActivity(
    classDestination: Class<C>, param: String, value: String,
    isFinish: Boolean = false
) {
    startActivity(Intent(this, classDestination).apply {
        putExtra(param, value)
    })

    if (isFinish) {
        finish()
    }
}

fun <C> Activity.showActivity(
    classDestination: Class<C>, param1: String, value1: String,
    param2: String, value2: String,
    isFinish: Boolean = false
) {
    startActivity(Intent(this, classDestination).apply {
        putExtra(param1, value1)
        putExtra(param2, value2)
    })

    if (isFinish) {
        finish()
    }
}

fun <C> Activity.showActivity(
    classDestination: Class<C>, param1: String, value1: String,
    param2: String, value2: String,
    param3: String, value3: String,
    isFinish: Boolean = false
) {
    startActivity(Intent(this, classDestination).apply {
        putExtra(param1, value1)
        putExtra(param2, value2)
        putExtra(param3, value3)
    })

    if (isFinish) {
        finish()
    }
}

fun Activity.showToast(message: String?, isLongDuration: Boolean = false) {
    runOnUiThread {
        val duration = if (isLongDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(this, message, duration).show()
    }
}

fun Activity.showSnackBar(view: View, message: String, isLongDuration: Boolean = false) {
    runOnUiThread {
        val duration = if (isLongDuration) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
        val snack = Snackbar.make(view, message, duration)
        val text = snack.view.findViewById<TextView>(R.id.snackbar_text)
        text.setTextColor(ContextCompat.getColor(this, R.color.colorRed))
        snack.show()
    }
}

fun <T> stringToObject(s: String, a: Class<T>): T {
    return Gson().fromJson(s, a)
}

fun objectToString(T: Any): String {
    return Gson().toJson(T)
}

fun Activity.openWebPage(url: String?) {
    var urlString = url
    if ((url?.contains("http://") == false) or (url?.contains("https://") == false)) {
        urlString = "https://$url"
    }
    val webpage: Uri = Uri.parse(urlString)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.share(message: String?) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, "Bagikan ke"))

}

fun copyToClipboard(context: Context, dataToCopy: String?, message: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(message, dataToCopy)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "$message berhasil di salin", Toast.LENGTH_SHORT).show()
}

fun ProgressBar.changeColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        indeterminateDrawable.colorFilter = BlendModeColorFilter(color, BlendMode.MULTIPLY)
    } else {
        indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }
}


fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.enable(activity: Activity?) {
    activity?.runOnUiThread { isEnabled = true }
}

fun View.disable(activity: Activity?) {
    activity?.runOnUiThread { isEnabled = false }
}

fun View.visible(activity: Activity?) {
    activity?.runOnUiThread { visibility = View.VISIBLE }
}

fun View.gone(activity: Activity?) {
    activity?.runOnUiThread { visibility = View.GONE }
}

fun View.invisible(activity: Activity?) {
    activity?.runOnUiThread { visibility = View.INVISIBLE }
}

fun SwipeRefreshLayout.show() {
    isRefreshing = true
}

fun SwipeRefreshLayout.hide() {
    isRefreshing = false
}