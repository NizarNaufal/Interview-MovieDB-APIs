package id.interview.newsapi.support

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object SupportObject {
    fun dateFormat(dateTimeString: String?): String {
        val dateFormatResult = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("ID"))
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        var d = Date()

        try {
            d = dateFormat.parse(dateTimeString)

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateFormatResult.format(d)
    }


    fun parseTimeUTC(input: String?, format: String, formatResult: String): String{
        if(input == null){
            return ""
        }
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

}