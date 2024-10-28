package com.d.quotesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesResult(
    @SerializedName("quote")
    val content:String,
    @SerializedName("author")
    val author:String
) : Parcelable
