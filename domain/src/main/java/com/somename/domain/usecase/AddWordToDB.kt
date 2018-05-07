package com.somename.domain.usecase

import com.somename.domain.DBRepository
import com.somename.domain.model.WordFromDB

import javax.inject.Inject
import javax.inject.Named

import io.reactivex.Observable
import io.reactivex.Scheduler

class AddWordToDB @Inject
constructor(@Named("ui_thread") executorThread: Scheduler,
            @Named("ui_thread") uiThread: Scheduler, private val mDBRepository: DBRepository) : UseCase<Boolean>(executorThread, uiThread) {

    private var mWord: WordFromDB? = null

    fun setWord(word: WordFromDB) {
        mWord = word
    }

    public override fun createObservableUseCase(): Observable<Boolean> {
        return mDBRepository.addWord(mWord!!)
    }
}
