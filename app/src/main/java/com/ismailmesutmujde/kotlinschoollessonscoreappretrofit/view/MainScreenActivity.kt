package com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.R
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.adapter.LessonScoresRecyclerViewAdapter
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.dao.LessonScoresDaoInterface
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.LessonScores
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.model.LessonScoresResponse
import com.ismailmesutmujde.kotlinschoollessonscoreappretrofit.service.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenActivity : AppCompatActivity() {
    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var lessonScoreList:ArrayList<LessonScores>
    private lateinit var adapter: LessonScoresRecyclerViewAdapter

    private lateinit var lsdi : LessonScoresDaoInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Lesson Score App"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        lsdi = ApiUtils.getLessonScoresDaoInterface()

        allLessonScores()

        /*
        lessonScoreList = ArrayList()

        val l1 = LessonScores(1,"History", 40, 80)
        val l2 = LessonScores(2,"Chemistry", 70, 50)
        val l3 = LessonScores(3,"Physics",30,60)

        lessonScoreList.add(l1)
        lessonScoreList.add(l2)
        lessonScoreList.add(l3)

        adapter = LessonScoresRecyclerViewAdapter(this, lessonScoreList)
        bindingMainScreen.recyclerView.adapter = adapter
        */


        bindingMainScreen.fab.setOnClickListener {
            val intent = Intent(this@MainScreenActivity, LessonScoreRecordScreenActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun allLessonScores() {
        lsdi.allLessonScores().enqueue(object : Callback<LessonScoresResponse>{
            override fun onResponse(
                call: Call<LessonScoresResponse>?,
                response: Response<LessonScoresResponse>?
            ) {
                if(response != null) {
                    val lessonScoresList = response.body()!!.lessonScores
                    adapter = LessonScoresRecyclerViewAdapter(this@MainScreenActivity, lessonScoresList)
                    bindingMainScreen.recyclerView.adapter = adapter

                    var sum = 0

                    for (ls in lessonScoresList) {
                        sum = sum + (ls.score1+ls.score2)/2
                    }
                    bindingMainScreen.toolbar.subtitle = "Average : ${sum/lessonScoresList.size}"

                }
            }

            override fun onFailure(call: Call<LessonScoresResponse>, t: Throwable) {

            }

        })
    }
}