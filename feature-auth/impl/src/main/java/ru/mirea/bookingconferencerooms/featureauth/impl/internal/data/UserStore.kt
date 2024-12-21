package ru.mirea.bookingconferencerooms.featureauth.impl.internal.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.di.AuthFeatureScope
import javax.inject.Inject

@AuthFeatureScope
internal class UserStore @Inject constructor(
    private val context: Context,
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user_id",
        scope = CoroutineScope(Dispatchers.Main),
    )

    suspend fun save(id: String, jwt: String) {
        val prefKey = stringPreferencesKey(KEY)
        val jwtPrefKey = stringPreferencesKey(JWT_KEY)
        context.dataStore.edit { pref ->
            pref[prefKey] = id
            pref[jwtPrefKey] = jwt
        }
    }

    suspend fun get(): String? {
        val prefKey = stringPreferencesKey(KEY)
        return context.dataStore.data.map { it[prefKey] }.firstOrNull()
    }

    suspend fun getJwt(): String? {
        val prefKey = stringPreferencesKey(JWT_KEY)
        return context.dataStore.data.map { it[prefKey] }.firstOrNull()
    }

    suspend fun remove() {
        val prefKey = stringPreferencesKey(KEY)
        val jwtPrefKey = stringPreferencesKey(JWT_KEY)
        context.dataStore.edit { pref ->
            pref.remove(prefKey)
            pref.remove(jwtPrefKey)
        }
    }

    private companion object {
        const val KEY = "user_id"
        const val JWT_KEY = "jwt"
    }
}