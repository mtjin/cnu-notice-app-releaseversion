package com.mtjin.cnunoticeapp.data.general.source

import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import io.reactivex.Flowable
import io.reactivex.Single

interface GeneralNoticeRepository {
    fun requestNotice(): Flowable<List<GeneralNotice>>
    fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>>
}