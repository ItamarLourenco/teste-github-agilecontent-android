package com.ilourenco.githubviewerandroid.App

/**
 * Created by itamarlourenco on 10/06/2018.
 */
import com.ilourenco.githubviewerandroid.App.Service.ReposService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    companion object{
        val HTTP_CODE_SUCCESS = 200
    }


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun reposService() = retrofit.create(ReposService::class.java)
}