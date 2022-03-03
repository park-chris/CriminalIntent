package com.crystal.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null ) {
            val fragment = CrimeListFragment.newInstance()

//            새로운 프래그먼트 트랜잭션 인스턴스를 생성하고 이 인스턴스에 add() 오퍼레이션을 포함시킨 후 커밋
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}