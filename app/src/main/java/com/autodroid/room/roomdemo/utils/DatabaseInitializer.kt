package com.autodroid.room.roomdemo.utils

import com.autodroid.room.roomdemo.database.AppDatabase
import android.os.AsyncTask
import android.util.Log
import com.autodroid.room.roomdemo.entity.User


class DatabaseInitializer {

    fun populateAsync(db: AppDatabase) {
        val task = PopulateDbAsync(db)
        task.execute()
    }


    class PopulateDbAsync internal constructor(private val db: AppDatabase) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            populateWithTestData(db);
            return null
        }

        private fun addUser(db: AppDatabase, user: User): User {
            db.userDao().insertAll(user)
            return user
        }

        private fun populateWithTestData(db: AppDatabase) {
            val user = User()
            user.firstName = "Sourabh"
            user.lastName = "Karkal"
            user.age = 28
            user.company = User.Company(1004, "Vuclip")

            addUser(db, user)

            val userList = db.userDao().getAll()
            Log.d("PopulateDbAsync", "Rows Count: " + userList.size)
        }

    }

}