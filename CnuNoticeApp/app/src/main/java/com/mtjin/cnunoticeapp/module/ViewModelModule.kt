package com.mtjin.cnunoticeapp.module

import com.mtjin.cnunoticeapp.views.bachelor.BachelorNoticeViewModel
import com.mtjin.cnunoticeapp.views.business.BusinessNoticeViewModel
import com.mtjin.cnunoticeapp.views.employ.EmployNoticeViewModel
import com.mtjin.cnunoticeapp.views.favorite.FavoriteViewModel
import com.mtjin.cnunoticeapp.views.general.GeneralNoticeViewModel
import com.mtjin.cnunoticeapp.views.login.LoginViewModel
import com.mtjin.cnunoticeapp.views.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { BachelorNoticeViewModel(get()) }
    viewModel { GeneralNoticeViewModel(get()) }
    viewModel { BusinessNoticeViewModel(get()) }
    viewModel { EmployNoticeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
}