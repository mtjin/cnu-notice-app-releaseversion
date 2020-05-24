package com.mtjin.cnunoticeapp.data.business.source

import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import io.reactivex.Flowable
import io.reactivex.Single

interface BusinessNoticeRepository {
    fun requestNotice(): Flowable<List<BusinessNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>>
}