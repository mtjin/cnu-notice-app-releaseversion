package com.mtjin.cnunoticeapp.module

import androidx.room.Room
import com.mtjin.cnunoticeapp.data.bachelor.source.local.BachelorNoticeDao
import com.mtjin.cnunoticeapp.data.bachelor.source.local.BachelorNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.bachelor.source.local.BachelorNoticeLocalDataSourceImpl
import com.mtjin.cnunoticeapp.data.business.source.local.BusinessNoticeDao
import com.mtjin.cnunoticeapp.data.business.source.local.BusinessNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.business.source.local.BusinessNoticeLocalDataSourceImpl
import com.mtjin.cnunoticeapp.data.employ.source.local.EmployNoticeDao
import com.mtjin.cnunoticeapp.data.employ.source.local.EmployNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.employ.source.local.EmployNoticeLocalDataSourceImpl
import com.mtjin.cnunoticeapp.data.favorite.source.local.FavoriteNoticeDao
import com.mtjin.cnunoticeapp.data.favorite.source.local.FavoriteNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.favorite.source.local.FavoriteNoticeLocalDataSourceImpl
import com.mtjin.cnunoticeapp.data.general.source.local.GeneralNoticeDao
import com.mtjin.cnunoticeapp.data.general.source.local.GeneralNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.general.source.local.GeneralNoticeLocalDataSourceImpl
import com.mtjin.cnunoticeapp.database.NoticeDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataModule: Module = module {
    single<BachelorNoticeLocalDataSource> { BachelorNoticeLocalDataSourceImpl(get()) }
    single<GeneralNoticeLocalDataSource> { GeneralNoticeLocalDataSourceImpl(get()) }
    single<BusinessNoticeLocalDataSource> { BusinessNoticeLocalDataSourceImpl(get()) }
    single<EmployNoticeLocalDataSource> { EmployNoticeLocalDataSourceImpl(get()) }
    single<FavoriteNoticeLocalDataSource> { FavoriteNoticeLocalDataSourceImpl(get()) }

    single<BachelorNoticeDao> { get<NoticeDatabase>().bachelorNoticeDao() }
    single<GeneralNoticeDao> { get<NoticeDatabase>().generalNoticeDao() }
    single<BusinessNoticeDao> { get<NoticeDatabase>().businessNoticeDao() }
    single<EmployNoticeDao> { get<NoticeDatabase>().employsNoticeDao() }
    single<FavoriteNoticeDao> { get<NoticeDatabase>().favoriteNoticeDao() }

    single<NoticeDatabase> {
        Room.databaseBuilder(
            get(),
            NoticeDatabase::class.java, "Notice.db"
        ).build()
    }
}