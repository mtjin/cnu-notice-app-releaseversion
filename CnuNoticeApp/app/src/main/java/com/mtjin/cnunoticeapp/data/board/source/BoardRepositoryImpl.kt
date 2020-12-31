package com.mtjin.cnunoticeapp.data.board.source

import com.mtjin.cnunoticeapp.data.board.source.local.BoardLocalDataSource

class BoardRepositoryImpl(private val local: BoardLocalDataSource) : BoardRepository {
    override var univAuth: Boolean
        get() = local.univAuth
        set(value) {
            local.univAuth = value
        }
}