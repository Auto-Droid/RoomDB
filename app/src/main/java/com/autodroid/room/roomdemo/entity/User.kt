package com.autodroid.room.roomdemo.entity

import androidx.room.*


@Entity(tableName = "USER")
class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Int? = null

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    @ColumnInfo(name = "age")
    var age: Int = 0

    @Embedded
    var company : Company? = null

    data class Company(val companyId: Int, val companyName: String) {}

}