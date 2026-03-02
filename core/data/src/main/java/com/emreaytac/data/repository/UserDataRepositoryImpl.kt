package com.emreaytac.data.repository

import com.emreaytac.datastore.MBPreferencesDataSource
import com.emreaytac.domain.repository.UserDataRepository
import com.emreaytac.model.DarkThemeConfig
import com.emreaytac.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val mbPreferencesDataSource: MBPreferencesDataSource
): UserDataRepository {

    override val userData: Flow<UserData> = mbPreferencesDataSource.userData

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        mbPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }
}