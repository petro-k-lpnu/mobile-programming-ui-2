import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.ui2.Workout

private const val SQL_MIGRATE_UP =
    "CREATE TABLE workouts (" +
            "id INTEGER PRIMARY KEY," +
            "title TEXT," +
            "distance INTEGER)"

private const val SQL_MIGRATE_DOWN = "DROP TABLE IF EXISTS workouts"

object WorkoutContract {
    // Table contents are grouped together in an anonymous object.
    object WorkoutEntry : BaseColumns {
        const val TABLE_NAME = "workouts"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DISTANCE = "distance"
    }
}


class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_MIGRATE_UP)

        var workoutList = mutableListOf<Workout>(
            Workout("Regular morning run. 21.05.2022", 10000),
            Workout("Evening recovery run. 23.05.2022", 5000),
            Workout("Morning long-distance run. 26.05.2022", 21000),
        )

        for (w in workoutList) {
            val values = ContentValues().apply {
                put(WorkoutContract.WorkoutEntry.COLUMN_NAME_TITLE, "Regular morning run. 21.05.2022")
                put(WorkoutContract.WorkoutEntry.COLUMN_NAME_DISTANCE, 10000)
            }

            db?.insert(WorkoutContract.WorkoutEntry.TABLE_NAME, null, values)
        }

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_MIGRATE_DOWN)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "workouts.db"
    }
}
