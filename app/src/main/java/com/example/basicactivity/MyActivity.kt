package com.example.basicactivity

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
//            .inMemory()
            .build()
        Realm.setDefaultConfiguration(configuration)
    }

}