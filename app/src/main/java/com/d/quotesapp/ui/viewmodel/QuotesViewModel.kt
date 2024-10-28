package com.d.quotesapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d.quotesapp.model.QuotesResult
import com.d.quotesapp.repository.QuotesRepository
import com.d.quotesapp.utils.CommonFunction
import com.d.quotesapp.utils.QuoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repository: QuotesRepository, @ApplicationContext private val context: Context
): ViewModel() {
    private val _response : MutableStateFlow<QuoteState<List<QuotesResult>?>> =
        MutableStateFlow(QuoteState.Loading)
    val quoteResponse: StateFlow<QuoteState<List<QuotesResult>?>> = _response

    private val _responseRandomQuote : MutableStateFlow<QuoteState<List<QuotesResult>?>> =
        MutableStateFlow(QuoteState.Loading)
    val randomQuoteResponse: StateFlow<QuoteState<List<QuotesResult>?>> = _responseRandomQuote

    init {
        fetchQuotes()
        fetchRandomQuotes()
    }

    private fun fetchQuotes() {
        viewModelScope.launch {
            if (CommonFunction.isNetworkAvailable(context)){
                try {
                    val response = repository.getQuotesRepo().first()
                    _response.emit(QuoteState.Success(response))
                } catch (e:Exception) {
                    val errorMessage = "$e"
                    _response.emit(QuoteState.Error(errorMessage))
                }
            } else{
                _response.emit(QuoteState.Error("No Internet Connection"))
            }
        }
    }

    fun fetchRandomQuotes() {
        viewModelScope.launch {
            if (CommonFunction.isNetworkAvailable(context)){
                try {
                    val response = repository.getQuotesRandomRepo().first()
                    _responseRandomQuote.emit(QuoteState.Success(response))
                } catch (e:Exception) {
                    val errorMessage = "$e"
                    _responseRandomQuote.emit(QuoteState.Error(errorMessage))
                }
            } else{
                _responseRandomQuote.emit(QuoteState.Error("No Internet Connection"))
            }
        }

    }


}