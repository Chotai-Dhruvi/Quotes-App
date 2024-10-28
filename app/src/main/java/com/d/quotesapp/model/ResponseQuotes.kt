package com.d.quotesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseQuotes(
    @SerializedName("quotes")
    val quotesResult: List<QuotesResult>  // List of quotes
) : Parcelable
