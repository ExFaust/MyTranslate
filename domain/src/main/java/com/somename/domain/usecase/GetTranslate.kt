package com.somename.domain.usecase


import com.somename.domain.NetworkRepository
import com.somename.domain.model.WordFromTranslator

import javax.inject.Inject
import javax.inject.Named

import io.reactivex.Observable
import io.reactivex.Scheduler

class GetTranslate @Inject
constructor(@Named("executor_thread") executorThread: Scheduler,
            @Named("ui_thread") uiThread: Scheduler, private val mNetworkRepository: NetworkRepository) : UseCase<WordFromTranslator>(executorThread, uiThread) {

    private var mWord: String = ""
    private var mLang: String = ""

    public override fun createObservableUseCase(): Observable<WordFromTranslator> {
        return this.mNetworkRepository.getTranslate(mWord,mLang)
    }

    fun setWordAndLang(word: String, lang: String) {
        mWord = word
        mLang = lang
    }
}
