package com.mtjin.cnunoticeapp.data.bachelor.source

import android.util.Log
import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import com.mtjin.cnunoticeapp.data.bachelor.source.local.BachelorNoticeLocalDataSource
import com.mtjin.cnunoticeapp.data.bachelor.source.remote.BachelorNoticeRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class BachelorNoticeRepositoryImpl(
    private val bachelorNoticeRemoteDataSource: BachelorNoticeRemoteDataSource,
    private val bachelorNoticeLocalDataSource: BachelorNoticeLocalDataSource
) : BachelorNoticeRepository {
    override fun requestNotice(): Flowable<List<BachelorNotice>> {
        return bachelorNoticeLocalDataSource.getNotices()
            .onErrorReturn { listOf() }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty()) {
                    requestBachelorNotice()
                        .toFlowable()
                        .onErrorReturn {
                            listOf()
                        }
                } else {
                    val local = Single.just(cachedMovies)
                    val remote = requestBachelorNotice()
                        .onErrorResumeNext {
                            local
                        }
                    Single.concat(local, remote)
                }
            }
    }

    private fun requestBachelorNotice(): Single<List<BachelorNotice>> {
        return bachelorNoticeRemoteDataSource.requestNotice()
            .flatMap {
                bachelorNoticeLocalDataSource.insertNotice(it)
                    .andThen(Single.just(it))
            }
    }

    override fun requestMoreNotice(offset: Int): Single<List<BachelorNotice>> {
        return bachelorNoticeRemoteDataSource.requestMoreNotice(offset)
    }
}