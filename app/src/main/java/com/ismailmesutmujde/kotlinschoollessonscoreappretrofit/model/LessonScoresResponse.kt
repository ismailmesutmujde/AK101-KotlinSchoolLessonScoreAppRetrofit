package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LessonScoresResponse (@SerializedName("notlar")
                          @Expose
                          var lessonScores : List<LessonScores>,
                          @SerializedName("success")
                          @Expose
                          var success:Int) {
}