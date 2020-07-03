package com.enterprise.test.data.network.pojo.driverinfo


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("average_rating")
    val averageRating: Int,
    @SerializedName("blocked_date")
    val blockedDate: String,
    @SerializedName("blocked_status")
    val blockedStatus: Int,
    @SerializedName("car_id")
    val carId: Int,
    @SerializedName("comment_blocked")
    val commentBlocked: Any,
    @SerializedName("comment_rating")
    val commentRating: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dispatcher_ids")
    val dispatcherIds: List<Int>,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_online")
    val isOnline: Boolean,
    @SerializedName("is_temporary_blocked")
    val isTemporaryBlocked: Boolean,
    @SerializedName("last_activity")
    val lastActivity: String,
    @SerializedName("last_location")
    val lastLocation: LastLocation,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("problem_status")
    val problemStatus: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("status")
    val status: Int
)