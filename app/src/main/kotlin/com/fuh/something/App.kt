package com.fuh.something

import android.app.Application
//import com.fuh.something.models.MyObjectBox
import com.jakewharton.threetenabp.AndroidThreeTen
import io.objectbox.BoxStore
import timber.log.Timber

/**
 * Created by nick on 15.09.17.
 */
class App : Application() {

    lateinit var boxStore: BoxStore
        private set

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            initTimber()
        }

        initObjectBox()
        initTreeTen()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initObjectBox() {
//        boxStore = MyObjectBox.builder().androidContext(this).build()
    }

    private fun initTreeTen() {
        AndroidThreeTen.init(this)
    }
}