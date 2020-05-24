package com.mtjin.cnunoticeapp.data.general.source.remote

import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import io.reactivex.Single

interface GeneralNoticeRemoteDataSource {
    fun requestNotice(): Single<List<GeneralNotice>>
    fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>>
}