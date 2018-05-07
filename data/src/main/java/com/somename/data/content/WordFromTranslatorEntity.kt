package com.somename.data.content

import com.google.gson.annotations.SerializedName

class WordFromTranslatorEntity {

    @SerializedName("code")
    var code: Int? = null

    @SerializedName("lang")
    var lang: String? = null

    @SerializedName("text")
    var text: List<String>? = null

}
