package com.mtjin.cnunoticeapp.data.employ.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EmployNoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<EmployNotice>): Completable

    @Query("SELECT * FROM employ  ORDER BY 1 DESC")
    fun getNotices(): Single<List<EmployNotice>>
}