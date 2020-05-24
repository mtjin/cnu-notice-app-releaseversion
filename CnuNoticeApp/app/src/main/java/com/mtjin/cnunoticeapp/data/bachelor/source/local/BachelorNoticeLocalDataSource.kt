package com.mtjin.cnunoticeapp.data.bachelor.source.local

import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import io.reactivex.Completable
import io.reactivex.Single

interface BachelorNoticeLocalDataSource {
    fun insertNotice(notice: List<BachelorNotice>) : Completable

    fun getNotices(): Single<List<BachelorNotice>>
}