package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.R
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.dao.LessonScoresDaoInterface
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.databinding.ActivityLessonScoreRecordScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.CRUDResponse
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.service.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LessonScoreRecordScreenActivity : AppCompatActivity() {
    private lateinit var bindingLessonScoreRecord : ActivityLessonScoreRecordScreenBinding

    private lateinit var lsdi : LessonScoresDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLessonScoreRecord = ActivityLessonScoreRecordScreenBinding.inflate(layoutInflater)
        val view = bindingLessonScoreRecord.root
        setContentView(view)

        bindingLessonScoreRecord.toolbarLessonScoreRecord.title = "Lesson Score Record"
        setSupportActionBar(bindingLessonScoreRecord.toolbarLessonScoreRecord)

        lsdi = ApiUtils.getLessonScoresDaoInterface()

        bindingLessonScoreRecord.buttonSave.setOnClickListener {
            val lesson_name = bindingLessonScoreRecord.editTextLesson.text.toString().trim()
            val score1 = bindingLessonScoreRecord.editTextScore1.text.toString().trim()
            val score2 = bindingLessonScoreRecord.editTextScore2.text.toString().trim()

            if(TextUtils.isEmpty(lesson_name)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter Lesson Name", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(score1)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter 1st Score", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(score2)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter 2nd Score", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lsdi.insertLessonScores(lesson_name, score1.toInt(), score2.toInt()).enqueue(object : Callback<CRUDResponse>{
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {

                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {

                }

            })

            val intent = Intent(this@LessonScoreRecordScreenActivity, MainScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}