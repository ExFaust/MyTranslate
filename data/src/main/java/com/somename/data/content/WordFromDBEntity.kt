package com.somename.data.content

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class WordFromDBEntity : RealmModel{

    var id: String? = null

    var langTranslatedWord: String? = null

    var langWord: String? = null

    var text: String? = null

    var translatedText: String? = null
}