package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.dao

import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.CRUDResponse
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.LessonScoresResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LessonScoresDaoInterface {
    @GET("notlar/tum_notlar.php")
    fun allLessonScores(): Call<LessonScoresResponse>

    @POST("notlar/delete_not.php")
    @FormUrlEncoded
    fun deleteLessonScores(@Field ("not_id") lesson_id:Int) : Call<CRUDResponse>

    @POST("notlar/insert_not.php")
    @FormUrlEncoded
    fun insertLessonScores(@Field ("ders_adi") lesson_name:String,
                           @Field ("not1") score1:Int,
                           @Field ("not2") score2:Int): Call<CRUDResponse>

    @POST("notlar/update_not.php")
    @FormUrlEncoded
    fun updateLessonScores(@Field ("not_id") lesson_id:Int,
                           @Field ("ders_adi") lesson_name:String,
                           @Field ("not1") score1:Int,
                           @Field ("not2") score2:Int): Call<CRUDResponse>
}