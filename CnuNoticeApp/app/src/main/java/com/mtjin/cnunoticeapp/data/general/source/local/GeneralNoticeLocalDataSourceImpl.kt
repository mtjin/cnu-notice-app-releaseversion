package com.mtjin.cnunoticeapp.data.general.source.local

import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import io.reactivex.Completable
import io.reactivex.Single

class GeneralNoticeLocalDataSourceImpl(private val bachelorNoticeDao: GeneralNoticeDao) :
    GeneralNoticeLocalDataSource {
    override fun insertNotice(notice: List<GeneralNotice>): Completable {
        return bachelorNoticeDao.insertNotice(notice)
    }

    override fun getNotices(): Single<List<GeneralNotice>> {
        return bachelorNoticeDao.getNotices()
    }
}