package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LessonScores (@SerializedName("not_id")
                         @Expose
                         var lesson_id : Int,
                         @SerializedName("ders_adi")
                         @Expose
                         var lesson_name : String,
                         @SerializedName("not1")
                         @Expose
                         var score1 : Int,
                         @SerializedName("not2")
                         @Expose
                         var score2 : Int) : Serializable {

}