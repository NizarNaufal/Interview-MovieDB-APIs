package id.interview.newsapi.view.home.support

interface IMoviesIteractor {
    fun getListNews(country:String,apiKey:String): Pair<Int, String?>
    fun getListCategory(country:String,category:String,apiKey:String): Pair<Int, String?>
}
interface IMoviesPresenter{
    fun getListNews(country:String,apiKey:String)
    fun getListCategory(country:String,category:String,apiKey:String)

}