package com.ilourenco.githubviewerandroid

/**
 * Created by itamarlourenco on 10/06/2018.
 */
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposService {
    @GET("{username}/repos")
    fun list(@Path("username") username: String): Call<List<Repos>>
}