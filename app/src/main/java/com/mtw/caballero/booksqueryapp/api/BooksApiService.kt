package com.mtw.diego.booksqueryapp.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {


    @GET("volumes?maxResults=10&printType=books&projection=lite")
    fun searchBooks(@Query("q") query: String): Observable<BooksModel.BooksApiResponse>

    companion object {
        private val baseUrl: String = "https://www.googleapis.com/books/v1/"

        fun create(): BooksApiService {
            return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build().create(BooksApiService::class.java)
        }
    }
}