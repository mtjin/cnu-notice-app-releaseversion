package com.mtjin.cnunoticeapp.data.general.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GeneralNoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<GeneralNotice>): Completable

    @Query("SELECT * FROM general  ORDER BY 1 DESC")
    fun getNotices(): Single<List<GeneralNotice>>
}