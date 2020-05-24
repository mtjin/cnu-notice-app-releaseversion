package com.mtjin.cnunoticeapp.data.employ.source.local

import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import io.reactivex.Completable
import io.reactivex.Single

class EmployNoticeLocalDataSourceImpl(private val noticeDao: EmployNoticeDao) :
    EmployNoticeLocalDataSource {
    override fun insertNotice(notice: List<EmployNotice>): Completable {
        return noticeDao.insertNotice(notice)
    }

    override fun getNotices(): Single<List<EmployNotice>> {
        return noticeDao.getNotices()
    }
}