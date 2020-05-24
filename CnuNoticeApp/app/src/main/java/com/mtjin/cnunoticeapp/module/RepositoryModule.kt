package com.mtjin.cnunoticeapp.module

import com.mtjin.cnunoticeapp.data.bachelor.source.BachelorNoticeRepository
import com.mtjin.cnunoticeapp.data.bachelor.source.BachelorNoticeRepositoryImpl
import com.mtjin.cnunoticeapp.data.business.source.BusinessNoticeRepository
import com.mtjin.cnunoticeapp.data.business.source.BusinessNoticeRepositoryImpl
import com.mtjin.cnunoticeapp.data.employ.source.EmployNoticeRepository
import com.mtjin.cnunoticeapp.data.employ.source.EmployNoticeRepositoryImpl
import com.mtjin.cnunoticeapp.data.favorite.source.FavoriteNoticeRepository
import com.mtjin.cnunoticeapp.data.favorite.source.FavoriteNoticeRepositoryImpl
import com.mtjin.cnunoticeapp.data.general.source.GeneralNoticeRepository
import com.mtjin.cnunoticeapp.data.general.source.GeneralNoticeRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<BachelorNoticeRepository> { BachelorNoticeRepositoryImpl(get(), get()) }
    single<GeneralNoticeRepository> { GeneralNoticeRepositoryImpl(get(), get()) }
    single<BusinessNoticeRepository> { BusinessNoticeRepositoryImpl(get(), get()) }
    single<EmployNoticeRepository> { EmployNoticeRepositoryImpl(get(), get()) }
    single<FavoriteNoticeRepository> { FavoriteNoticeRepositoryImpl(get()) }
}