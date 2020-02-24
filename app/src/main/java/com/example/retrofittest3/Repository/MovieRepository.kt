package com.example.retrofittest3.Repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofittest3.api.*
import com.example.retrofittest3.database.*


class MovieRepository (private val movieDao: MovieDao){

    init{
        Log.i("tag", "MovieRepositoryInitialized@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@MovieRepositoryInitialized")
    }
    val movieList =  MutableLiveData<List<Movie>>()
    private lateinit var result : Result

    private var castClient: MovieDBCastService =
        CastRetrofitBuilder.apiService

    suspend fun getCast(movieId:Int) = castClient.getActors(movieId)

    private var movieByActorClient : MovieDBMoviebyActorService =
        MovieByActorRetrofitBuilder.apiService

    suspend fun getMoviesByActor(actorId : Int): A_CastInfo {
    //TODO funktion macht zwei sachen?
        val castInfo : A_CastInfo = movieByActorClient.getMovies(actorId)
        val movieList = castInfo.movie_credits.movieListByActor
        insertMovies(movieList)
        return castInfo
    }

    private var movieClient : MovieDBService = MovieRetrofitBuilder.apiService

    suspend fun getMoviesFromWebKotlinStyle() : Result {

        try {
            result = movieClient.getMovies()
            val movieList = result.movieList
            insertMovies(movieList)

        }catch (ex:Exception){

        }
        return result
    }




   /* fun getMovies(context: Context): LiveData<List<Movie>>{
        return if (InternetUtils.isInternetAvailable(context)){
            Log.d("MovieRepo ", "We are in getMoviesFromWebservice")
            getMoviesFromWebservice()
        } else {
            Log.d("MovieRepo ", "We are in getMoviesFromDatabase")
            getMoviesFromDatabase()
        }
    }*/
    
   /*private fun getMoviesFromWebservice() : MutableLiveData<List<Movie>>{

        val retrofitCall = MovieRetrofitBuilder.apiService.getMovies()
        retrofitCall.enqueue(object: Callback<Result>{

            override fun onFailure(call: Call<Result>, t: Throwable) {
                movieListByActor.value = null
            }
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
               if (response.code() == 200) {
                   movieListByActor.value = response.body()?.movieListByActor
               }
            }
        })
        return movieListByActor
    }*/

    fun getMoviesFromDatabase(): LiveData<List<Movie>> {
        Log.i("Tag","taking from Database @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

         return movieDao.getMovieList()
    }

    suspend fun insertMovies(movies : List<Movie>){
        movieDao.insertAll(movies)
         Log.i("tag","writing to database _____---------------")
    }
    suspend fun deleteAll(){
         movieDao.deleteAll()
     }
    fun getMovieById(id : Int): LiveData<Movie> {

        return movieDao.getMoviebyId(id)
    }

    suspend fun deleteMoviebyId(id: Int){
        movieDao.deleteMovieById(id)
    }

    fun getMovieByRoomId(roomId : Int) : LiveData<Movie>{
        return movieDao.getMovieByRoomId(roomId)
    }
    suspend fun getRawMovieByRoomId(roomId : Int) : Movie {
        return movieDao.getRawMovieByRoomId(roomId)
    }
    suspend fun getRawMoviebyId(id : Int) : Movie {
        return movieDao.getRawMoviebyId(id)
    }
    suspend fun getNextMovieUp(roomId: Int) :Movie {
       return movieDao.getNextMovieUp(roomId)
    }

    suspend fun getNextMovieDown(roomId: Int) :Movie {
        return movieDao.getNextMovieDown(roomId)
    }
}