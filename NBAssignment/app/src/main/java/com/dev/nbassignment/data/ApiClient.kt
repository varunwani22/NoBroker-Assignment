package com.dev.nbassignment.data

import com.dev.nbassignment.model.ResponseModel
import retrofit2.http.GET

import retrofit2.Call;


interface ApiClient {

    /**
     * declaration of End point
     */

    @GET("b/60fa8fefa917050205ce5470")
    suspend fun getAllItems(): ResponseModel
}