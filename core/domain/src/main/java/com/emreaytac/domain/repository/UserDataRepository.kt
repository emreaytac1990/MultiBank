package com.emreaytac.domain.repository

import com.emreaytac.model.DarkThemeConfig
import com.emreaytac.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>


    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}