package com.mtjin.cnunoticeapp.data.favorite.source.local

import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteNoticeLocalDataSourceImpl(private val favoriteNoticeDao: FavoriteNoticeDao) :
    FavoriteNoticeLocalDataSource {
    override fun insertNotice(notice: FavoriteNotice): Completable {
        return favoriteNoticeDao.insertNotice(notice)
    }

    override fun getNotices(): Single<List<FavoriteNotice>> {
        return favoriteNoticeDao.getNotices()
    }

    override fun deleteNotice(notice: FavoriteNotice): Completable {
        return favoriteNoticeDao.deleteNotice(notice)
    }
}