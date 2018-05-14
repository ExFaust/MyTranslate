package com.somename.mytranslate.content.mapper

import com.somename.data.repository.datasource.mapper.Mapper
import com.somename.domain.model.WordFromDB
import com.somename.mytranslate.content.WordFromDBViewModel

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordFromDBViewModelMapper @Inject
constructor() : Mapper<WordFromDB, WordFromDBViewModel>() {

    override fun map(value: WordFromDB): WordFromDBViewModel {
        val wordFromDBViewModel = WordFromDBViewModel()
        wordFromDBViewModel.langWord = value.langWord
        wordFromDBViewModel.langTranslatedWord = value.langTranslatedWord
        wordFromDBViewModel.text = value.text
        wordFromDBViewModel.translatedText = value.translatedText
        wordFromDBViewModel.id = value.id
        return wordFromDBViewModel
    }

    override fun reverseMap(value: WordFromDBViewModel): WordFromDB {
        val wordFromDB = WordFromDB()
        wordFromDB.langWord = value.langWord
        wordFromDB.langTranslatedWord = value.langTranslatedWord
        wordFromDB.text = value.text
        wordFromDB.translatedText = value.translatedText
        wordFromDB.id = value.id
        return wordFromDB
    }
}
