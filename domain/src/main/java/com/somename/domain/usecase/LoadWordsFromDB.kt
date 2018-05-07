package com.somename.domain.usecase


import com.somename.domain.DBRepository
import com.somename.domain.model.WordFromDB
import javax.inject.Inject
import javax.inject.Named

import io.reactivex.Observable
import io.reactivex.Scheduler

class LoadWordsFromDB @Inject
constructor(@Named("ui_thread") executorThread: Scheduler,
            @Named("ui_thread") uiThread: Scheduler, private val mDBRepository: DBRepository) : UseCase<List<WordFromDB>>(executorThread, uiThread) {

    public override fun createObservableUseCase(): Observable<List<WordFromDB>> {
        return mDBRepository.loadWords()
    }
}
