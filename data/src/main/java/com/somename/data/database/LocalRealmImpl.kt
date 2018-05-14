package com.somename.data.database


import com.somename.data.BuildConfig
import com.somename.data.content.WordFromDBEntity
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.realm.Realm

class LocalRealmImpl(@param:NonNull private val mRealm: Realm) : LocalRealm {

    override fun addWord(word: WordFromDBEntity): Observable<Boolean> {
        return Observable.create { emitter ->
            addToRealm(word)
            emitter.onComplete()
        }
    }

    override fun loadWords(): Observable<List<WordFromDBEntity>> {
        return Observable.create<List<WordFromDBEntity>> { emitter ->
            emitter.onNext(loadFromRealm())
            emitter.onComplete()
        }
    }

    override fun searchWord(word: String): Observable<List<WordFromDBEntity>> {
        return Observable.create<List<WordFromDBEntity>> { emitter ->
            emitter.onNext(searchInRealm(word))
            emitter.onComplete()
        }
    }

    override fun removeWord(word: WordFromDBEntity): Observable<List<WordFromDBEntity>> {
        return Observable.create<List<WordFromDBEntity>> { emitter ->
            emitter.onNext(removeFromRealm(word))
            emitter.onComplete()
        }
    }

    private fun searchInRealm(word: String): List<WordFromDBEntity> {
        return mRealm.where(WordFromDBEntity::class.java)
                .contains("text", word)
                .or()
                .contains("translatedText", word)
                .findAll()
    }

    private fun addToRealm(word: WordFromDBEntity) {
        mRealm.beginTransaction()
        mRealm.copyToRealm(word)
        mRealm.commitTransaction()
    }

    private fun loadFromRealm(): List<WordFromDBEntity> {
        return mRealm.where(WordFromDBEntity::class.java).findAll()
    }

    private fun removeFromRealm(word: WordFromDBEntity): List<WordFromDBEntity> {
        mRealm.executeTransaction { realm1 ->
            val result = realm1.where(WordFromDBEntity::class.java).equalTo(BuildConfig.ID_FIELD, word.id).findAll()
            result.deleteAllFromRealm()
        }
        return mRealm.where(WordFromDBEntity::class.java).findAll()
    }
}
