package com.mtjin.cnunoticeapp.data.favorite.source

import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteNoticeRepository {
    fun requestNotice(): Single<List<FavoriteNotice>>
    fun insertNotice(notice: FavoriteNotice) : Completable
    fun deleteNotice(notice: FavoriteNotice): Completable
}