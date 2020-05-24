package com.mtjin.cnunoticeapp.data.business.source.local

import android.util.Log
import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import io.reactivex.Completable
import io.reactivex.Single

class BusinessNoticeLocalDataSourceImpl(private val noticeDao: BusinessNoticeDao) :
    BusinessNoticeLocalDataSource {
    override fun insertNotice(notice: List<BusinessNotice>): Completable {
        return noticeDao.insertNotice(notice)
    }

    override fun getNotices(): Single<List<BusinessNotice>> {
        Log.d("AAAAA", "" + noticeDao.getNotices())
        return noticeDao.getNotices()
    }
}