package com.crystal.android.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel: ViewModel() {

//    저장된 요소의 값을 변경할 수 있는 MutableList 타입
//    <Crime> 제네릭 타입을 지정해서 Crime객체만 저장가능
    val crimes = mutableListOf<Crime>()

//    init 초기화 블록은 CrimeListViewModel 인스턴스가 생성될 때 자동 실행됨
   init {
        for (i in 0 until 100) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crime.requiresPolice = i % 2
            crimes += crime
        }
    }
}