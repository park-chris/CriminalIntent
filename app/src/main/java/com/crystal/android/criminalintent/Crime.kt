package com.crystal.android.criminalintent

import java.util.*

// UUID는 안드로이드 프레임워크에 포함된 유틸리티 클래스로 고유한 ID 값을 쉽게 생성하는 방법 제공
data class Crime(val id: UUID = UUID.randomUUID(),
                 var title: String ="",
                 var date: Date = Date(),
                 var isSolved: Boolean = false,
                 var requiresPolice: Int = 0) {

}