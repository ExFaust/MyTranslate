package com.somename.data.repository.datasource;

import com.somename.data.database.LocalRealmImpl;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class RealmDataSourceFactory {

    private LocalRealmImpl mLocalRealm;

    @Inject
    public RealmDataSourceFactory(@NonNull LocalRealmImpl localRealm) {
        mLocalRealm = localRealm;
    }

    public RealmDataSource createDataSource() {
        return new RealmDataSource(mLocalRealm);
    }
}

