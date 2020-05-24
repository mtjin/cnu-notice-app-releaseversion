package com.mtjin.cnunoticeapp.data.business.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BusinessNoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(notice: List<BusinessNotice>): Completable

    @Query("SELECT * FROM business  ORDER BY 1 DESC")
    fun getNotices(): Single<List<BusinessNotice>>
}