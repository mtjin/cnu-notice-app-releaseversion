package com.mtjin.cnunoticeapp.data.favorite.source.local

import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteNoticeLocalDataSource {
    fun insertNotice(notice: FavoriteNotice): Completable
    fun getNotices(): Single<List<FavoriteNotice>>
    fun deleteNotice(notice: FavoriteNotice): Completable
}