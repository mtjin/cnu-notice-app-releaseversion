package com.mtjin.cnunoticeapp.data.business.source.local

import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import io.reactivex.Completable
import io.reactivex.Single

interface BusinessNoticeLocalDataSource {
    fun insertNotice(notice: List<BusinessNotice>): Completable
    fun getNotices(): Single<List<BusinessNotice>>
}