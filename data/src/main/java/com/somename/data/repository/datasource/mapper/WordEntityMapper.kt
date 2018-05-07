package com.somename.data.repository.datasource.mapper

import com.somename.data.content.WordFromTranslatorEntity
import com.somename.domain.model.WordFromTranslator

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordEntityMapper @Inject
constructor() : Mapper<WordFromTranslator, WordFromTranslatorEntity>() {

    override fun reverseMap(value: WordFromTranslatorEntity): WordFromTranslator {
        val separateLang = value.lang?.split("-".toRegex())
        val wordFromTranslator = WordFromTranslator()
        wordFromTranslator.code = value.code
        wordFromTranslator.langWord = separateLang?.get(0)
        wordFromTranslator.langTranslatedWord = separateLang?.get(1)
        wordFromTranslator.text = value.text?.get(0)
        return wordFromTranslator
    }

    override fun map(value: WordFromTranslator): WordFromTranslatorEntity {
        throw UnsupportedOperationException()
    }

}
