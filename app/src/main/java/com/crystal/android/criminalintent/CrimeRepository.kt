package com.crystal.android.criminalintent

import android.content.Context
import androidx.room.Room
import com.crystal.android.criminalintent.database.CrimeDatabase
import java.util.*

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    private val database : CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val crimeDao = database.crimeDao()

    fun getCrimes(): List<Crime> = crimeDao.getCrimes()
    fun getCrime(id: UUID): Crime? = crimeDao.getCrime(id)

    companion object {

//        private으로 생성자를 지정해서 외부에서 CrimeRepository.initialize를 호출해야만 CrimeRepository 인스턴스를 생성할수있음음
       private var INSTANCE: CrimeRepository? = null

//        CrimeRepository 인스턴스 생성
        fun  initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

//        CrimeRepository 인스턴스가 생성되지 않으면 IllegalStateException을 발생
//        >> 앱이 시작될 때 레포지터리인 CrimeRepository 인스턴스를 생성해야한다.
        fun get():CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }

    }

}