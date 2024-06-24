package com.yyusufsefa.videogameappcompose.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.yyusufsefa.videogameappcompose.core.constant.Constants
import com.yyusufsefa.videogameappcompose.domain.manager.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    private val context: Context
) : DataStoreManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.ON_BOARDING_SHOWN)

    override suspend fun saveOnBoardingShown(isShown: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.ONBOARDING_SHOWN_KEY] = isShown
        }
    }

    override fun getOnBoardingShown(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.ONBOARDING_SHOWN_KEY] ?: false
        }
    }

    private object PreferenceKeys {
        val ONBOARDING_SHOWN_KEY = booleanPreferencesKey("onboarding_shown")
    }
}
