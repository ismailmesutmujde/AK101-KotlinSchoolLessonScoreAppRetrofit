package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.service

import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.dao.LessonScoresDaoInterface
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.retrofit.RetrofitClient

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getLessonScoresDaoInterface() : LessonScoresDaoInterface {
            return  RetrofitClient.getClient(BASE_URL).create(LessonScoresDaoInterface::class.java)
        }
    }
}