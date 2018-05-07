package com.somename.mytranslate.screen.presenter


import com.somename.domain.model.WordFromDB
import com.somename.domain.usecase.AddWordToDB
import com.somename.domain.usecase.GetTranslate
import com.somename.domain.usecase.LoadWordsFromDB
import com.somename.domain.usecase.SearchWordFromDB
import com.somename.mytranslate.content.WordFromDBViewModel
import com.somename.mytranslate.content.mapper.WordFromDBViewModelMapper

import javax.inject.Inject

import io.reactivex.observers.DisposableObserver


class ScrollingPresenter @Inject
constructor(private val mAddWordToDB: AddWordToDB, private val mGetTranslate: GetTranslate,
            private val mLoadWordsFromDB: LoadWordsFromDB, private val mSearchWordFromDB: SearchWordFromDB,
            private val mWordFromDBViewModelMapper: WordFromDBViewModelMapper) : Presenter<ScrollingPresenter.View>() {

    fun init() {
        view?.showLoading()
        mLoadWordsFromDB.execute(object : DisposableObserver<List<WordFromDB>>(){
            override fun onNext(words: List<WordFromDB>) {
                view?.showWords(mWordFromDBViewModelMapper.map(words))
            }

            override fun onError(e: Throwable) {
                view?.hideLoading()
                view?.showError()
                e.printStackTrace()
            }

            override fun onComplete() {
                view?.hideLoading()
            }
        })
    }

    fun searchWord(word: String) {
        mSearchWordFromDB.setWord(word)
        mSearchWordFromDB.execute(object : DisposableObserver<List<WordFromDB>>(){
            override fun onNext(words: List<WordFromDB>) {
                view?.showWords(mWordFromDBViewModelMapper.map(words))
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
        mLoadWordsFromDB.dispose()
        mAddWordToDB.dispose()
        mGetTranslate.dispose()
        mSearchWordFromDB.dispose()
    }

    interface View : Presenter.View {

        fun showWords(wordsFromDBViewModel: List<WordFromDBViewModel>)

    }

}
