package com.mtjin.cnunoticeapp.data.board.source.local

import com.mtjin.cnunoticeapp.utils.SharedPrefManager

class BoardLocalDataSourceImpl(private val sharedPrefManager: SharedPrefManager) :
    BoardLocalDataSource {
    override var univAuth: Boolean
        get() = sharedPrefManager.univAuth
        set(value) {
            sharedPrefManager.univAuth = value
        }
}