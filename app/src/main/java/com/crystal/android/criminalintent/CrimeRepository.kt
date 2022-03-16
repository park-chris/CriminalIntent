package com.crystal.android.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.crystal.android.criminalintent.database.CrimeDatabase
import com.crystal.android.criminalintent.database.migration_1_2
import java.io.File
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    private val database : CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2).build()

    private val crimeDao = database.crimeDao()
    private val fileDir = context.applicationContext.filesDir

//    새로운 스레드를 참조하는 executors 인스턴스를 반환하는 함수
    private val executor = Executors.newSingleThreadExecutor()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    fun updateCrime(crime: Crime) {
        executor.execute{
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    fun getPhotoFile(crime: Crime): File = File(fileDir, crime.photoFileName)

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