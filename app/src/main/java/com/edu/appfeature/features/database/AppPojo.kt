package com.edu.appfeature.features.database

data class ApplicationPojo(
    val applicationId: Long,
    val applicationName: String,
    val publishedDate: String,
    val descriptions: String,
    val imageUrl: String,
    val videoUrl: String,
    val views: Long,
    val numOfLike: Int
)

data class UserPojo(
    val userId: Long? = null,
    val userName: String? = null,
    val email: String? = null,
    val password: String? = null,
    val registerDate: String? = null,
    val profilePicture: String? = null,
)