package id.interview.newsapi.view.home.support

interface IStoriesIteractor {
    fun getStoriesNews(apiKey:String): Pair<Int, String?>
}
interface IStoriesPresenter{
    fun getStoriesNews(apiKey:String)
}