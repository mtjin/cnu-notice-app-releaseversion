package com.mtjin.cnunoticeapp.data.employ.source.remote

import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import io.reactivex.Single

interface EmployNoticeRemoteDataSource {
    fun requestNotice(): Single<List<EmployNotice>>
    fun requestMoreNotice(offset: Int): Single<List<EmployNotice>>
}