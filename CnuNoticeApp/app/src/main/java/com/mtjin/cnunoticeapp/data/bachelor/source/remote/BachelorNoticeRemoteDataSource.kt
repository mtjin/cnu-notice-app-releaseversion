package com.mtjin.cnunoticeapp.data.bachelor.source.remote

import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import io.reactivex.Single

interface BachelorNoticeRemoteDataSource {
    fun requestNotice(): Single<List<BachelorNotice>>
    fun requestMoreNotice(offset: Int): Single<List<BachelorNotice>>
}