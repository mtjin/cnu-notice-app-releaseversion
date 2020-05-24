package com.mtjin.cnunoticeapp.data.bachelor.source.local

import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import io.reactivex.Completable
import io.reactivex.Single

class BachelorNoticeLocalDataSourceImpl(private val bachelorNoticeDao: BachelorNoticeDao) :
    BachelorNoticeLocalDataSource {
    override fun insertNotice(notice: List<BachelorNotice>): Completable {
        return bachelorNoticeDao.insertNotice(notice)
    }

    override fun getNotices(): Single<List<BachelorNotice>> {
        return bachelorNoticeDao.getNotices()
    }
}