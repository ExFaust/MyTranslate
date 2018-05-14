package com.somename.mytranslate.screen.presenter


import com.somename.domain.model.WordFromDB
import com.somename.domain.usecase.*
import com.somename.mytranslate.content.WordFromDBViewModel
import com.somename.mytranslate.content.mapper.WordFromDBViewModelMapper

import javax.inject.Inject

import io.reactivex.observers.DisposableObserver


class ScrollingPresenter @Inject
constructor(private val mLoadWordsFromDB: LoadWordsFromDB, private val mSearchWordFromDB: SearchWordFromDB,
            private val mWordFromDBViewModelMapper: WordFromDBViewModelMapper, private val mRemoveWordFromDB: RemoveWordFromDB) : Presenter<ScrollingPresenter.View>() {

    fun init() {
        view?.showLoading()
        mLoadWordsFromDB.execute(object : DisposableObserver<List<WordFromDB>>() {
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
        mSearchWordFromDB.execute(object : DisposableObserver<List<WordFromDB>>() {
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

    fun removeWord(word: WordFromDBViewModel) {
        mRemoveWordFromDB.setWord(mWordFromDBViewModelMapper.reverseMap(word))
        mRemoveWordFromDB.execute(object : DisposableObserver<List<WordFromDB>>() {
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
        mSearchWordFromDB.dispose()
        mRemoveWordFromDB.dispose()
    }

    interface View : Presenter.View {

        fun showWords(wordsFromDBViewModel: List<WordFromDBViewModel>)

    }

}
