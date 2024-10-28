package com.d.quotesapp.api

import com.d.quotesapp.api.Constants.Companion.List_Of_quotes
import com.d.quotesapp.api.Constants.Companion.random_quotes
import com.d.quotesapp.model.QuotesResult
import retrofit2.http.GET

interface ApiService {
    @GET(List_Of_quotes)
    suspend fun getListOfQuotes(): List<QuotesResult>

    @GET(random_quotes)
    suspend fun getRandomQuotes(): List<QuotesResult>

}