package com.somename.data.repository.datasource.mapper

import com.somename.data.content.WordFromDBEntity
import com.somename.domain.model.WordFromDB
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordFromDBEntityMapper @Inject
constructor() : Mapper<WordFromDB, WordFromDBEntity>() {

    override fun reverseMap(value: WordFromDBEntity): WordFromDB {
        val wordFromDB = WordFromDB()
        wordFromDB.langWord = value.langWord
        wordFromDB.langTranslatedWord = value.langTranslatedWord
        wordFromDB.text = value.text
        wordFromDB.translatedText = value.translatedText
        wordFromDB.id = value.id
        return wordFromDB
    }

    override fun map(value: WordFromDB): WordFromDBEntity {
        val wordFromDBEntity = WordFromDBEntity()
        wordFromDBEntity.langWord = value.langWord
        wordFromDBEntity.langTranslatedWord = value.langTranslatedWord
        wordFromDBEntity.text = value.text
        wordFromDBEntity.translatedText = value.translatedText
        wordFromDBEntity.id = value.id
        return wordFromDBEntity
    }

}