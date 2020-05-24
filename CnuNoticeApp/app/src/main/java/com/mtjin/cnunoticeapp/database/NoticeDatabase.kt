package com.mtjin.cnunoticeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import com.mtjin.cnunoticeapp.data.bachelor.source.local.BachelorNoticeDao
import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import com.mtjin.cnunoticeapp.data.business.source.local.BusinessNoticeDao
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import com.mtjin.cnunoticeapp.data.employ.source.local.EmployNoticeDao
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.data.favorite.source.local.FavoriteNoticeDao
import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import com.mtjin.cnunoticeapp.data.general.source.local.GeneralNoticeDao

@Database(
    entities = [BachelorNotice::class, GeneralNotice::class, BusinessNotice::class, EmployNotice::class, FavoriteNotice::class],
    version = 1,
    exportSchema = false
)
abstract class NoticeDatabase : RoomDatabase() {
    abstract fun bachelorNoticeDao(): BachelorNoticeDao
    abstract fun generalNoticeDao(): GeneralNoticeDao
    abstract fun businessNoticeDao(): BusinessNoticeDao
    abstract fun employsNoticeDao(): EmployNoticeDao
    abstract fun favoriteNoticeDao(): FavoriteNoticeDao
}