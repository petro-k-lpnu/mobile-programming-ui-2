package com.example.ui2

import DbHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DbHelper(this).readableDatabase

        val projection = arrayOf(
            BaseColumns._ID,
            WorkoutContract.WorkoutEntry.COLUMN_NAME_TITLE,
            WorkoutContract.WorkoutEntry.COLUMN_NAME_DISTANCE
        )

        val cursor = dbHelper.query(
            WorkoutContract.WorkoutEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val workoutList = mutableListOf<Workout>()
        with(cursor) {
            while (moveToNext()) {

                if (cursor!!.moveToFirst()) {
                    while (!cursor.isAfterLast) {
                        val titleIndex =
                            cursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_NAME_TITLE)
                        val title = cursor.getString(titleIndex)

                        val distanceIndex =
                            cursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_NAME_DISTANCE)
                        val distance = cursor.getString(
                            distanceIndex
                        )

                        workoutList.add(Workout(title, distance = distance.toInt()))
                        cursor.moveToNext()
                    }
                }


            }
        }
        cursor.close()


        val adapter = WorkoutAdapter(workoutList)
        rvWorkoutList.adapter = adapter
        rvWorkoutList.layoutManager = LinearLayoutManager(this)


    }
}