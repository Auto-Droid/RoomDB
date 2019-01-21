package com.autodroid.room.roomdemo

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.autodroid.room.roomdemo.database.AppDatabase
import com.autodroid.room.roomdemo.entity.User
import com.autodroid.room.roomdemo.utils.DatabaseInitializer


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInsert = findViewById<Button>(R.id.btnInsert)
        btnInsert.setOnClickListener {
            insertData()
        }

        val btnGetData = findViewById<Button>(R.id.btnGetData)
        btnGetData.setOnClickListener {
            getData()
        }
    }

    private fun insertData() {
        DatabaseInitializer().populateAsync(AppDatabase.getInstance(this)!!)
    }

    private fun getData() {
        val tvDbResult = findViewById<TextView>(R.id.tvDbResult)

        doAsync {
            val db = AppDatabase.getInstance(this)
            val users: List<User> = db?.userDao()?.getAll()!!
            val stringBuilder = StringBuilder();
            for (user in users) {
                stringBuilder.append("\n")
                stringBuilder.append("Uid : ").append(user.uid)
                stringBuilder.append("\n")
                stringBuilder.append("Name : ").append(user.firstName).append(" ").append(user.lastName)
                stringBuilder.append("\n")
                stringBuilder.append("Age : ").append(user.age)
                stringBuilder.append("\n")
                stringBuilder.append("Company ID : ").append(user.company?.companyId)
                stringBuilder.append("\n")
                stringBuilder.append("Company Name : ").append(user.company?.companyName)
            }

            tvDbResult.post {
                tvDbResult.text = stringBuilder.toString()
            }

        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        init {
            execute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}
