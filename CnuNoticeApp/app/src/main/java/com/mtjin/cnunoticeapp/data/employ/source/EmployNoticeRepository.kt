package com.mtjin.cnunoticeapp.data.employ.source

import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import io.reactivex.Flowable
import io.reactivex.Single

interface EmployNoticeRepository {
    fun requestNotice(): Flowable<List<EmployNotice>>
    fun requestMoreNotice(offset: Int): Single<List<EmployNotice>>
}