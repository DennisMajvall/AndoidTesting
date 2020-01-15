package com.example.basicactivity.retrofit

import com.example.basicactivity.User
import io.reactivex.Single
import retrofit2.http.*

const val apiKey = "ed3da575ea342e591fc219b429c3c33c"
const val privateKey = "9985cf0115724bc7aacb0cb7c41ba2a172b178de"
const val ts = "1"
val HASH = (ts + privateKey + apiKey).md5()

// Read more about this at: https://developer.marvel.com/documentation/authorization

interface MarvelService {
    @GET("characters?limit=1&ts=$ts&apikey=$apiKey")
    fun getAllCharacters(@Query("hash") hash: String = HASH): Single<CharacterDataWrapper>

    @GET("group/{id}/users")
    fun groupList(@Path("id") groupId: Int): Single<List<User>>

    @GET("group/{id}/users")
    fun groupList(@Path("id") groupId: Int, @Query("sort") sort: String): Single<List<User>>

    @POST("users/new")
    fun createUser(@Body user: User): Single<User>
}
