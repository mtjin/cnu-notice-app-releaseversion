package com.mtjin.cnunoticeapp.data.employ.source.local

import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import io.reactivex.Completable
import io.reactivex.Single

interface EmployNoticeLocalDataSource {
    fun insertNotice(notice: List<EmployNotice>): Completable
    fun getNotices(): Single<List<EmployNotice>>
}