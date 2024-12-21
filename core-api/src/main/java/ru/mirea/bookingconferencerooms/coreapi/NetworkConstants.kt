package ru.mirea.bookingconferencerooms.coreapi

object NetworkConstants {
    private const val BASE_URL = "https://hive.mrdekk.ru/todo"
    const val LIST_URL = "$BASE_URL/list"
    fun getItemUrl(id: String) = "$LIST_URL/$id"

    const val USER_API_KEY = "user_api_key"
    fun getAuthHeader(key: String) = "OAuth $key"
}