package com.mtjin.cnunoticeapp.data.business.source

import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import com.mtjin.cnunoticeapp.data.business.source.local.BusinessNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.business.source.remote.BusinessNoticeRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class BusinessNoticeRepositoryImpl(
    private val noticeRemoteDataSource: BusinessNoticeRemoteDataSource,
    private val noticeLocalDataSource: BusinessNoticeLocalDataSource
) : BusinessNoticeRepository {
    override fun requestNotice(): Flowable<List<BusinessNotice>> {
        return noticeLocalDataSource.getNotices()
            .onErrorReturn {
                listOf()
            }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty()) {
                    requestBusinessNotice()
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else {
                    val local = Single.just(cachedMovies)
                    val remote = requestBusinessNotice()
                        .onErrorResumeNext { local }
                    Single.concat(local, remote)
                }
            }
    }

    private fun requestBusinessNotice(): Single<List<BusinessNotice>> {
        return noticeRemoteDataSource.requestNotice()
            .flatMap {
                noticeLocalDataSource.insertNotice(it)
                    .andThen(Single.just(it))
            }
    }

    override fun requestMoreNotice(offset: Int): Single<List<BusinessNotice>> {
        return noticeRemoteDataSource.requestMoreNotice(offset)
    }
}