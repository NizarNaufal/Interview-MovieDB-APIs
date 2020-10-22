package id.interview.moviedb.view.movies.support

interface IMoviesIteractor {
    fun getListNews(country:String,apiKey:String): Pair<Int, String?>
}
interface IMoviesPresenter{
    fun getListNews(country:String,apiKey:String)
}