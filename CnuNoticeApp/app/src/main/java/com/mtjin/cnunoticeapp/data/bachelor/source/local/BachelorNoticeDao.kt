package com.mtjin.cnunoticeapp.data.bachelor.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BachelorNoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<BachelorNotice>): Completable

    @Query("SELECT * FROM bachelor  ORDER BY 1 DESC")
    fun getNotices(): Single<List<BachelorNotice>>
}