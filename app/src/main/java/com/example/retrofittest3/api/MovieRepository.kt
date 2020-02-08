package com.example.retrofittest3.api


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofittest3.database.MovieDao
import com.example.retrofittest3.database.Movie
import com.example.retrofittest3.database.Result
import com.example.retrofittest3.util.InternetUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository (private val movieDao: MovieDao){

    init{
        Log.i("tag", "MovieRepositoryInitialized@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@MovieRepositoryInitialized")
    }
    val movieList =  MutableLiveData<List<Movie>>()


    fun getMovies(context: Context): LiveData<List<Movie>>{
        return if (InternetUtils.isInternetAvailable(context)){
            Log.d("MovieRepo ", "We are in getMoviesFromWebservice")
            getMoviesFromWebservice()
        } else {
            Log.d("MovieRepo ", "We are in getMoviesFromDatabase")
            getMoviesFromDatabase()
        }
    }
    
   private fun getMoviesFromWebservice() : MutableLiveData<List<Movie>>{

        val retrofitCall = MovieRetrofitBuilder.apiService.getMovies()


        retrofitCall.enqueue(object: Callback<Result>{

            override fun onFailure(call: Call<Result>, t: Throwable) {
                movieList.value = null
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
               if (response.code() == 200) {
                   movieList.value = response.body()?.movieList

               }
            }
        })
        return movieList
    }

     fun getMoviesFromDatabase(): LiveData<List<Movie>> {
        Log.i("Tag","taking from Database @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

         return movieDao.getMovieList()
    }

     suspend fun insertMovies(movies : List<Movie>){
        movieDao.insertAll(movies)
         Log.i("tag","ruuuuuuuuunnnnniiiiiing")
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
}