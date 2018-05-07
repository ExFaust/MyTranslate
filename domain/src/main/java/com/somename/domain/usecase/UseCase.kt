package com.somename.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class UseCase<T>(private val executorThread: Scheduler, private val uiThread: Scheduler) {

    private val compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }

    fun execute(disposableObserver: DisposableObserver<T>?) {

        if (disposableObserver == null) {
            throw IllegalArgumentException("disposableObserver must not be null")
        }

        val observable = this.createObservableUseCase().subscribeOn(executorThread).observeOn(uiThread)

        val observer = observable.subscribeWith(disposableObserver)
        compositeDisposable.add(observer)
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected abstract fun createObservableUseCase(): Observable<T>
}
