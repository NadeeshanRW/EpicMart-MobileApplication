package com.Exsample.epicmart

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class FirebaceOffline: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}