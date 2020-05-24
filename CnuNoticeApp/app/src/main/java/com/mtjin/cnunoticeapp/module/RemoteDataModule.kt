package com.mtjin.cnunoticeapp.module

import com.mtjin.cnunoticeapp.data.bachelor.source.remote.BachelorNoticeRemoteDataSource
import com.mtjin.cnunoticeapp.data.bachelor.source.remote.BachelorNoticeRemoteDataSourceImpl
import com.mtjin.cnunoticeapp.data.business.source.remote.BusinessNoticeRemoteDataSource
import com.mtjin.cnunoticeapp.data.business.source.remote.BusinessNoticeRemoteDataSourceImpl
import com.mtjin.cnunoticeapp.data.employ.source.remote.EmployNoticeRemoteDataSource
import com.mtjin.cnunoticeapp.data.employ.source.remote.EmployNoticeRemoteDataSourceImpl
import com.mtjin.cnunoticeapp.data.general.source.remote.GeneralNoticeRemoteDataSource
import com.mtjin.cnunoticeapp.data.general.source.remote.GeneralNoticeRemoteDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteDataModule: Module = module {
    single<BachelorNoticeRemoteDataSource> { BachelorNoticeRemoteDataSourceImpl() }
    single<GeneralNoticeRemoteDataSource> { GeneralNoticeRemoteDataSourceImpl() }
    single<BusinessNoticeRemoteDataSource> { BusinessNoticeRemoteDataSourceImpl() }
    single<EmployNoticeRemoteDataSource> { EmployNoticeRemoteDataSourceImpl() }
}