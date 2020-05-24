package com.mtjin.cnunoticeapp.data.favorite.source.local

import androidx.room.*
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteNoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: FavoriteNotice): Completable

    @Query("SELECT * FROM favorite ORDER BY 1 DESC")
    fun getNotices(): Single<List<FavoriteNotice>>

    @Delete
    fun deleteNotice(notice: FavoriteNotice): Completable
}