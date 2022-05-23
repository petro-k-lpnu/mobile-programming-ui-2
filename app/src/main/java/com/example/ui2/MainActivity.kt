package com.example.ui2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var workoutList = mutableListOf<Workout>(
            Workout("Regular morning run. 21.05.2022", 10000),
            Workout("Evening recovery run. 23.05.2022", 5000),
            Workout("Morning long-distance run. 26.05.2022", 21000),
        )

        val adapter = WorkoutAdapter(workoutList)
        rvWorkoutList.adapter = adapter
        rvWorkoutList.layoutManager = LinearLayoutManager(this)
    }
}