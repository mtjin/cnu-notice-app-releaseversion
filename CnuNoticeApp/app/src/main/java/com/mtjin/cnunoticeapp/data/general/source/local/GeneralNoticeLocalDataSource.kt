package com.mtjin.cnunoticeapp.data.general.source.local

import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import io.reactivex.Completable
import io.reactivex.Single

interface GeneralNoticeLocalDataSource {
    fun insertNotice(notice: List<GeneralNotice>): Completable
    fun getNotices(): Single<List<GeneralNotice>>
}