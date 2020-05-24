package com.mtjin.cnunoticeapp.data.bachelor.source

import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import io.reactivex.Flowable
import io.reactivex.Single

interface BachelorNoticeRepository {
    fun requestNotice(): Flowable<List<BachelorNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BachelorNotice>>
}