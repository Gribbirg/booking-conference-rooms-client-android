package ru.mirea.bookingconferencerooms.featureauth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class User(
    val id: String,
    val login: String,
    @SerialName("default_email")
    val email: String,
    @SerialName("display_name")
    val name: String,
    @SerialName("default_avatar_id")
    val avatar: String,
) {
    fun avatarUrl(size: AvatarSize): String {
        return "https://avatars.yandex.net/get-yapic/$avatar/${size.jsonValue}"
    }
}
