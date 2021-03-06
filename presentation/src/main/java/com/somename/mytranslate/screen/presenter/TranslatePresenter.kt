package com.somename.mytranslate.screen.presenter

import com.somename.domain.model.WordFromDB
import com.somename.domain.model.WordFromTranslator
import com.somename.domain.usecase.AddWordToDB
import com.somename.domain.usecase.GetTranslate
import com.somename.domain.usecase.SearchWordFromDB
import com.somename.mytranslate.content.WordFromDBViewModel
import com.somename.mytranslate.content.WordFromTranslatorViewModel
import com.somename.mytranslate.content.mapper.WordFromDBViewModelMapper
import com.somename.mytranslate.content.mapper.WordFromTranslatorViewModelMapper
import io.reactivex.observers.DisposableObserver
import java.util.*
import javax.inject.Inject

class TranslatePresenter @Inject
constructor(private val mAddWordToDB: AddWordToDB, private val mGetTranslate: GetTranslate,
            private val mWordFromDBViewModelMapper: WordFromDBViewModelMapper, private val mSearchWordFromDB: SearchWordFromDB,
            private val mWordFromTranslatorViewModelMapper: WordFromTranslatorViewModelMapper) : Presenter<TranslatePresenter.View>() {

    fun addWord(word: WordFromTranslatorViewModel, originalText: String) {
        val wordFromDBViewModel = WordFromDBViewModel()
        wordFromDBViewModel.langWord = word.langWord
        wordFromDBViewModel.langTranslatedWord = word.langTranslatedWord
        wordFromDBViewModel.translatedText = word.text
        wordFromDBViewModel.text = originalText
        wordFromDBViewModel.id = UUID.randomUUID().toString()
        mAddWordToDB.setWord(mWordFromDBViewModelMapper.reverseMap(wordFromDBViewModel))
        mAddWordToDB.execute(object : DisposableObserver<Boolean>() {
            override fun onNext(boolean: Boolean) {

            }

            override fun onError(e: Throwable) {

                view?.showError()
                e.printStackTrace()
            }

            override fun onComplete() {
                view?.goBack()
            }
        })
    }

    fun searchWord(word: WordFromTranslatorViewModel, originalText: String) {
        mSearchWordFromDB.setWord(originalText)
        mSearchWordFromDB.execute(object : DisposableObserver<List<WordFromDB>>() {
            override fun onNext(words: List<WordFromDB>) {
                if (words.isNotEmpty())
                    view?.showMessage(originalText)
                else
                    addWord(word, originalText)
            }

            override fun onError(e: Throwable) {
                view?.showError()
                e.printStackTrace()
            }

            override fun onComplete() {
            }
        })
    }

    fun getTranslate(text: String, lang: String) {
        mGetTranslate.setWordAndLang(text, lang)
        mGetTranslate.execute(object : DisposableObserver<WordFromTranslator>() {
            override fun onNext(word: WordFromTranslator) {
                view?.showTranslate(mWordFromTranslatorViewModelMapper.map(word), text)
            }

            override fun onError(e: Throwable) {
                view?.showError()
                e.printStackTrace()
            }

            override fun onComplete() {
            }
        })
    }

    fun onDestroy() {
        mAddWordToDB.dispose()
        mGetTranslate.dispose()
    }

    interface View : Presenter.View {

        fun showTranslate(wordFromTranslatorViewModel: WordFromTranslatorViewModel, originalText: String)

        fun showMessage(originalText: String)

        fun goBack()

    }

}