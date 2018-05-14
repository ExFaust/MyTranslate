package com.somename.domain.usecase

import com.somename.domain.DBRepository
import com.somename.domain.model.WordFromDB
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class RemoveWordFromDB @Inject
constructor(@Named("ui_thread") executorThread: Scheduler,
            @Named("ui_thread") uiThread: Scheduler, private val mDBRepository: DBRepository) : UseCase<List<WordFromDB>>(executorThread, uiThread) {

    private var mWord: WordFromDB? = null

    public override fun createObservableUseCase(): Observable<List<WordFromDB>> {
        return mDBRepository.removeWord(mWord!!)
    }

    fun setWord(word: WordFromDB) {
        mWord = word
    }
}