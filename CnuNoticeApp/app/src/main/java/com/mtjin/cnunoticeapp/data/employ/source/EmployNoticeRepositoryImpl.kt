package com.mtjin.cnunoticeapp.data.employ.source

import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import com.mtjin.cnunoticeapp.data.employ.source.local.EmployNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.employ.source.remote.EmployNoticeRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class EmployNoticeRepositoryImpl(
    private val noticeRemoteDataSource: EmployNoticeRemoteDataSource,
    private val noticeLocalDataSource: EmployNoticeLocalDataSource
) : EmployNoticeRepository {
    override fun requestNotice(): Flowable<List<EmployNotice>> {
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

    private fun requestBusinessNotice(): Single<List<EmployNotice>> {
        return noticeRemoteDataSource.requestNotice()
            .flatMap {
                noticeLocalDataSource.insertNotice(it)
                    .andThen(Single.just(it))
            }
    }

    override fun requestMoreNotice(offset: Int): Single<List<EmployNotice>> {
        return noticeRemoteDataSource.requestMoreNotice(offset)
    }
}