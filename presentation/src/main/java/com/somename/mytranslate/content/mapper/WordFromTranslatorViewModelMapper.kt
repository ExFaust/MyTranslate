package com.somename.mytranslate.content.mapper

import com.somename.data.repository.datasource.mapper.Mapper
import com.somename.domain.model.WordFromTranslator
import com.somename.mytranslate.content.WordFromTranslatorViewModel

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordFromTranslatorViewModelMapper @Inject
constructor() : Mapper<WordFromTranslator, WordFromTranslatorViewModel>() {

    override fun map(value: WordFromTranslator): WordFromTranslatorViewModel {
        val wordFromTranslator = WordFromTranslatorViewModel()
        wordFromTranslator.code = value.code
        wordFromTranslator.langWord = value.langWord
        wordFromTranslator.langTranslatedWord = value.langTranslatedWord
        wordFromTranslator.text = value.text
        return wordFromTranslator
    }

    override fun reverseMap(value: WordFromTranslatorViewModel): WordFromTranslator {
        throw UnsupportedOperationException()
    }
}
