package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.R
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.dao.LessonScoresDaoInterface
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.databinding.ActivityLessonScoreDetailScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.CRUDResponse
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.LessonScores
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.service.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LessonScoreDetailScreenActivity : AppCompatActivity() {
    private lateinit var bindingLessonScoreDetailScreen : ActivityLessonScoreDetailScreenBinding

    private lateinit var lessonScore : LessonScores
    private lateinit var lsdi : LessonScoresDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLessonScoreDetailScreen = ActivityLessonScoreDetailScreenBinding.inflate(layoutInflater)
        val view = bindingLessonScoreDetailScreen.root
        setContentView(view)

        bindingLessonScoreDetailScreen.toolbarLessonScoreDetail.title = "Lesson Score Detail"
        setSupportActionBar(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail)

        lsdi = ApiUtils.getLessonScoresDaoInterface()

        lessonScore = intent.getSerializableExtra("obj") as LessonScores

        bindingLessonScoreDetailScreen.editTextLessonDetail.setText(lessonScore.lesson_name)
        bindingLessonScoreDetailScreen.editTextScore1Detail.setText((lessonScore.score1).toString())
        bindingLessonScoreDetailScreen.editTextScore2Detail.setText((lessonScore.score2).toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete -> {
                Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail, "Delete it?",
                    Snackbar.LENGTH_SHORT)
                    .setAction("YES") {
                        lsdi.deleteLessonScores(lessonScore.lesson_id).enqueue(object: Callback<CRUDResponse>{
                            override fun onResponse(
                                call: Call<CRUDResponse>,
                                response: Response<CRUDResponse>
                            ) {

                            }

                            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {

                            }

                        })
                        startActivity(Intent(this@LessonScoreDetailScreenActivity, MainScreenActivity::class.java))
                        finish()
                    }.show()
                return true
            }
            R.id.action_edit -> {
                val lesson_name = bindingLessonScoreDetailScreen.editTextLessonDetail.text.toString().trim()
                val score1 = bindingLessonScoreDetailScreen.editTextScore1Detail.text.toString().trim()
                val score2 = bindingLessonScoreDetailScreen.editTextScore2Detail.text.toString().trim()

                if(TextUtils.isEmpty(lesson_name)){
                    Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail,"Enter Lesson Name",
                        Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(score1)){
                    Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail,"Enter 1st Score",
                        Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(score2)){
                    Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail,"Enter 2nd Score",
                        Snackbar.LENGTH_SHORT).show()
                    return false
                }


                lsdi.updateLessonScores(lessonScore.lesson_id,lesson_name, score1.toInt(), score2.toInt()).enqueue(object : Callback<CRUDResponse>{
                    override fun onResponse(
                        call: Call<CRUDResponse>,
                        response: Response<CRUDResponse>
                    ) {

                    }

                    override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {

                    }

                })
                val intent = Intent(this@LessonScoreDetailScreenActivity, MainScreenActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> return false
        }
    }
}