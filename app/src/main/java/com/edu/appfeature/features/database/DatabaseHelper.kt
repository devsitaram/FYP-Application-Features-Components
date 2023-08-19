package com.edu.appfeature.features.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.sql.SQLException

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VIRION) {

    // create the companion object
    companion object {
        // initialize the variable
        private const val DATABASE_NAME = "ApplicationDB"
        private const val DATABASE_VIRION = 1

        // user table
        const val USER_TABLE = "user"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "email"
        const val USER_PASSWORD = "email"
        const val REGISTER_DATE = "register_date"
        const val PROFILE_PICTURE = "profile_picture"

        //
        const val APP_TABLE = "application"
        const val APP_ID = "app_id"
        const val APP_NAME = "app_name"
        const val PUBLISHED_DATE = "published_date"
        const val DESCRIPTIONS = "descriptionS"
        const val IMAGE_URL = "imageUrl"
        const val VIDEO_URL = "videoUrl"
        const val VIEWS = "views"
        const val NUM_OF_LIKE = "numOfLike"
    }

    // create the database table
    override fun onCreate(db: SQLiteDatabase) {
        // user table
        db.execSQL(
            "CREATE TABLE $USER_TABLE(" +
                    "$USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$USER_NAME TEXT," +
                    "$USER_EMAIL TEXT UNIQUE," +
                    "$USER_PASSWORD TEXT," +
                    "$REGISTER_DATE TEXT," +
                    "$PROFILE_PICTURE TEXT" +
                    ")"
        )
        // application table
        db.execSQL(
            "CREATE TABLE $APP_TABLE(" +
                    "$APP_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$APP_NAME TEXT UNIQUE," +
                    "$PUBLISHED_DATE TEXT," +
                    "$DESCRIPTIONS TEXT," +
                    "$REGISTER_DATE TEXT," +
                    "$IMAGE_URL TEXT," +
                    "$VIDEO_URL TEXT," +
                    "$VIEWS TEXT," +
                    "$NUM_OF_LIKE TEXT" +
                    ")"
        )
    }

    // CRUD statements
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // drop the database table
        db.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
        db.execSQL("DROP TABLE IF EXISTS $APP_TABLE")
        onCreate(db)
    }

    /**
     * the given below function is to user or admin
     * the first function is insert user details
     * second function is get user details
     * third function is update
     * last function is delete
     */
    // insert the user
    fun registerUser(
        name: String,
        email: String,
        password: String,
        date: String,
        imageUrl: String
    ): Boolean? {
        return try {
            val sqLiteWrite = this.writableDatabase // write only Insert, update, delete query
            val values = ContentValues()
            values.put(USER_NAME, name)
            values.put(USER_EMAIL, email)
            values.put(USER_PASSWORD, password)
            values.put(REGISTER_DATE, date)
            values.put(PROFILE_PICTURE, imageUrl)

            sqLiteWrite.insert(USER_TABLE, null, values) // insert the user data in database
            sqLiteWrite.close()
            true
        } catch (ex: SQLException) {
            false
        }
    }

    // get data from database
    @SuppressLint("Range")
    fun getUserDetails(context: Context): ArrayList<UserPojo> {
        val userLists = ArrayList<UserPojo>()
        val sqLiteDatabaseRead = this.readableDatabase
        val cursor = sqLiteDatabaseRead.rawQuery("SELECT * FROM $USER_TABLE", null)
        while (cursor.moveToNext()) {
            // store the data in variables
            val userId = cursor.getLong(cursor.getColumnIndex(USER_ID))
            val userName = cursor.getString(cursor.getColumnIndex(USER_NAME))
            val email = cursor.getString(cursor.getColumnIndex(USER_EMAIL))
            val password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD))
            val registerDate = cursor.getString(cursor.getColumnIndex(REGISTER_DATE))
            val profile = cursor.getString(cursor.getColumnIndex(PROFILE_PICTURE))

            // add all details to the list
            userLists.add(
                UserPojo(
                    userId = userId,
                    userName = userName,
                    email = email,
                    password = password,
                    registerDate = registerDate,
                    profilePicture = profile
                )
            )
        }
        cursor.close()
        sqLiteDatabaseRead.close()
        if (userLists.isEmpty()) {
            Toast.makeText(
                context,
                "The database has no data!\nडाटाबेससँग कुनै डाटा छैन!",
                Toast.LENGTH_SHORT
            ).show()
        }
        return userLists
    }

    // get email
    fun deleteByUserId(customerId: Long?): Boolean {
        val dbWritable = this.writableDatabase
        val deletedRows =
            dbWritable.delete(USER_TABLE, "$USER_ID = ?", arrayOf(customerId.toString()))
        dbWritable.close()
        return deletedRows > 0
    }

    @SuppressLint("Recycle")
    fun deleteUser(name: String?): Boolean {
        val deleteDB: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = deleteDB.rawQuery(
            "SELECT $USER_NAME FROM $USER_TABLE WHERE $USER_NAME = ?",
            arrayOf(name)
        )
        return if (cursor.count > 0) {
            Log.e("Name:-> ", "$name")
            deleteDB.execSQL(
                "DELETE FROM $USER_TABLE WHERE $USER_NAME = ?",
                listOf(name).toTypedArray()
            )
            Log.e("Result:-> ", "Deleted successfully")
            true
        } else {
            false
        }
    }

    // update password
    fun updateUserDetails(
        id: Long?,
        name: String?,
        email: String?,
        password: String?,
        date: String?,
        picture: String?
    ): Boolean {
        val sqLiteDatabaseRead = this.readableDatabase
        val sqLiteDatabaseWrite = this.writableDatabase
        try {
            val cursor: Cursor =
                sqLiteDatabaseRead.rawQuery("SELECT $USER_ID FROM $USER_TABLE", null)
            while (cursor.moveToNext()) {
                val customerId = cursor.getLong(0)
                if (id == customerId) {
                    val contentValues = ContentValues()
                    contentValues.put(USER_ID, id)
                    contentValues.put(USER_NAME, name)
                    contentValues.put(USER_EMAIL, email)
                    contentValues.put(USER_PASSWORD, password)
                    contentValues.put(REGISTER_DATE, date)
                    contentValues.put(PROFILE_PICTURE, picture)

                    sqLiteDatabaseWrite.update(
                        USER_TABLE,
                        contentValues,
                        "$USER_ID = ?",
                        arrayOf(id.toString())
                    )
                    cursor.close()
                    return true
                }
            }
            cursor.close()
            return false
        } catch (ex: SQLException) {
            return false
        }
    }


    /**
     * The given below all the function are applications
     * the first function is register
     * the second function is get
     * the third function is update
     * the last function is delete
     */
    fun registerApplication(
        appName: String,
        publishedDate: String,
        descriptions: String,
        registerDate: String,
        imageUrl: String,
        videoUrl: String,
        views: String,
        numOfLike: String
    ): Boolean? {
        return try {
            val sqLiteWrite = this.writableDatabase // write only Insert, update, delete query
            val values = ContentValues()
            values.put(APP_NAME, appName)
            values.put(PUBLISHED_DATE, publishedDate)
            values.put(DESCRIPTIONS, descriptions)
            values.put(REGISTER_DATE, registerDate)
            values.put(IMAGE_URL, imageUrl)
            values.put(VIDEO_URL, videoUrl)
            values.put(VIEWS, views)
            values.put(NUM_OF_LIKE, numOfLike)

            sqLiteWrite.insert(USER_TABLE, null, values) // insert the user data in database
            sqLiteWrite.close()
            true
        } catch (ex: SQLException) {
            false
        }
    }

    // get application details
    @SuppressLint("Range")
    fun getAppDetails(context: Context): ArrayList<ApplicationPojo> {
        val appDetailLists = ArrayList<ApplicationPojo>()
        val sqLiteDatabaseRead = this.readableDatabase
        val cursor = sqLiteDatabaseRead.rawQuery("SELECT * FROM $APP_TABLE", null)
        while (cursor.moveToNext()) {
            // store the data in variables
            val appId = cursor.getLong(cursor.getColumnIndex(APP_ID))
            val appName = cursor.getString(cursor.getColumnIndex(APP_NAME))
            val publishedDate = cursor.getString(cursor.getColumnIndex(PUBLISHED_DATE))
            val descriptions = cursor.getString(cursor.getColumnIndex(DESCRIPTIONS))
            val imageUrl = cursor.getString(cursor.getColumnIndex(IMAGE_URL))
            val videoUrl = cursor.getString(cursor.getColumnIndex(VIDEO_URL))
            val views = cursor.getLong(cursor.getColumnIndex(VIEWS))
            val numOfLike = cursor.getInt(cursor.getColumnIndex(NUM_OF_LIKE))

            // add all details to the list
            appDetailLists.add(
                ApplicationPojo(
                    applicationId = appId,
                    applicationName = appName,
                    publishedDate = publishedDate,
                    descriptions = descriptions,
                    imageUrl = imageUrl,
                    videoUrl = videoUrl,
                    views = views,
                    numOfLike = numOfLike
                )
            )
        }
        cursor.close()
        sqLiteDatabaseRead.close()
        if (appDetailLists.isEmpty()) {
            Toast.makeText(
                context,
                "The database has no data!\nडाटाबेससँग कुनै डाटा छैन!",
                Toast.LENGTH_SHORT
            ).show()
        }
        return appDetailLists
    }
}