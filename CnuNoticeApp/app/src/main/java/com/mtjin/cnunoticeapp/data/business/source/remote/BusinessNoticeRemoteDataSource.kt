package com.mtjin.cnunoticeapp.data.business.source.remote

import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import io.reactivex.Single

interface BusinessNoticeRemoteDataSource {
    fun requestNotice(): Single<List<BusinessNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>>
}