package com.bobby.gads2020leaderboard.network

import com.bobby.gads2020leaderboard.network.response.LearningLeader
import com.bobby.gads2020leaderboard.network.response.SkillIQLeader
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("api/hours")
    suspend fun getLearningLeaders(): Response<List<LearningLeader>>

    @GET("api/skilliq")
    suspend fun getSkillIQLeaders(): Response<List<SkillIQLeader>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitProject(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email: String,
        @Field("entry.284483984") githubLink: String
    ):Response<Void>
}