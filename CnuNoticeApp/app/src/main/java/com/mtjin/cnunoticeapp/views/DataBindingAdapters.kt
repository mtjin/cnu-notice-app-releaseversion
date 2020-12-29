package com.mtjin.cnunoticeapp.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import com.mtjin.cnunoticeapp.utils.EndlessRecyclerViewScrollListener
import com.mtjin.cnunoticeapp.views.bachelor.BachelorAdapter
import com.mtjin.cnunoticeapp.views.bachelor.BachelorNoticeViewModel
import com.mtjin.cnunoticeapp.views.business.BusinessAdapter
import com.mtjin.cnunoticeapp.views.business.BusinessNoticeViewModel
import com.mtjin.cnunoticeapp.views.employ.EmployAdapter
import com.mtjin.cnunoticeapp.views.employ.EmployNoticeViewModel
import com.mtjin.cnunoticeapp.views.employ.FavoriteAdapter
import com.mtjin.cnunoticeapp.views.general.GeneralAdapter
import com.mtjin.cnunoticeapp.views.general.GeneralNoticeViewModel

@BindingAdapter("setBachelorItems")
fun RecyclerView.setBachelorAdapterItems(items: List<BachelorNotice>?) {
    with((adapter as BachelorAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}

@BindingAdapter("setGeneralItems")
fun RecyclerView.setGeneralAdapterItems(items: List<GeneralNotice>?) {
    with((adapter as GeneralAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}

@BindingAdapter("setBusinessItems")
fun RecyclerView.setBusinessAdapterItems(items: List<BusinessNotice>?) {
    with((adapter as BusinessAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}

@BindingAdapter("setEmployItems")
fun RecyclerView.setEmployAdapterItems(items: List<EmployNotice>?) {
    with((adapter as EmployAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}

@BindingAdapter("setFavoriteItems")
fun RecyclerView.setFavoriteAdapterItems(items: List<FavoriteNotice>?) {
    with((adapter as FavoriteAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}

@BindingAdapter("bachelorEndlessScroll")
fun RecyclerView.setBachelorEndlessScroll(
    viewModel: BachelorNoticeViewModel
) {
    val scrollListener =
        object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.requestMoreNotice(totalItemsCount + 1)
            }
        }
    this.addOnScrollListener(scrollListener)
}

@BindingAdapter("generalEndlessScroll")
fun RecyclerView.setGeneralEndlessScroll(
    viewModel: GeneralNoticeViewModel
) {
    val scrollListener =
        object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.requestMoreNotice(totalItemsCount + 1)
            }
        }
    this.addOnScrollListener(scrollListener)
}

@BindingAdapter("businessEndlessScroll")
fun RecyclerView.setBusinessEndlessScroll(
    viewModel: BusinessNoticeViewModel
) {
    val scrollListener =
        object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.requestMoreNotice(totalItemsCount + 1)
            }
        }
    this.addOnScrollListener(scrollListener)
}

@BindingAdapter("employEndlessScroll")
fun RecyclerView.setEmployEndlessScroll(
    viewModel: EmployNoticeViewModel
) {
    val scrollListener =
        object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.requestMoreNotice(totalItemsCount + 1)
            }
        }
    this.addOnScrollListener(scrollListener)
}