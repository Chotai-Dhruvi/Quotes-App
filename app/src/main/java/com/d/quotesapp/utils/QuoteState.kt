package com.d.quotesapp.utils

sealed class QuoteState<out T> {
    data class Success<T>(val data: T) : QuoteState<T>()
    data class Error(val message:String) : QuoteState<Nothing>()
    object Loading : QuoteState<Nothing>()
}