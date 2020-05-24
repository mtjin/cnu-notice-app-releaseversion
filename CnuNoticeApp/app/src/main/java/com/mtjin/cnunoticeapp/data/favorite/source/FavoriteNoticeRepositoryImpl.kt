package com.mtjin.cnunoticeapp.data.favorite.source

import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.data.favorite.source.local.FavoriteNoticeLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteNoticeRepositoryImpl(
    private val localDataSource: FavoriteNoticeLocalDataSource
) : FavoriteNoticeRepository {
    override fun requestNotice(): Single<List<FavoriteNotice>> {
        return localDataSource.getNotices()
    }

    override fun insertNotice(notice: FavoriteNotice): Completable {
        return localDataSource.insertNotice(notice)
    }

    override fun deleteNotice(notice: FavoriteNotice): Completable {
        return localDataSource.deleteNotice(notice)
    }
}