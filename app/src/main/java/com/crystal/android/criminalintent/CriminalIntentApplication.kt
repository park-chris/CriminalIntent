package com.crystal.android.criminalintent

import android.app.Application

class CriminalIntentApplication : Application() {

//    앱이 최초로 메모리에 로드될때 안드로이드 시스템이 자동 호출
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }

}