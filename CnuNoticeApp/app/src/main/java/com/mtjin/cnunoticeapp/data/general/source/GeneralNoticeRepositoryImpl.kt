package com.mtjin.cnunoticeapp.data.general.source

import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import com.mtjin.cnunoticeapp.data.general.source.local.GeneralNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.general.source.remote.GeneralNoticeRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class GeneralNoticeRepositoryImpl(
    private val generalNoticeRemoteDataSource: GeneralNoticeRemoteDataSource,
    private val generalNoticeLocalDataSource: GeneralNoticeLocalDataSource
) : GeneralNoticeRepository {
    override fun requestNotice(): Flowable<List<GeneralNotice>> {
        return generalNoticeLocalDataSource.getNotices()
            .onErrorReturn { listOf() }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty()) {
                    requestGeneralNotice()
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else {
                    val local = Single.just(cachedMovies)
                    val remote = requestGeneralNotice()
                        .onErrorResumeNext { local }
                    Single.concat(local, remote)
                }
            }
    }

    private fun requestGeneralNotice(): Single<List<GeneralNotice>> {
        return generalNoticeRemoteDataSource.requestNotice()
            .flatMap {
                generalNoticeLocalDataSource.insertNotice(it)
                    .andThen(Single.just(it))
            }
    }

    override fun requestMoreNotice(offset: Int): Single<List<GeneralNotice>> {
        return generalNoticeRemoteDataSource.requestMoreNotice(offset)
    }
}