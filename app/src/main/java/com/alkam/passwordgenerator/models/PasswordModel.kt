package com.alkam.passwordgenerator.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// модель информации о пароле
@Entity(tableName = "passwords_table")
data class PasswordModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var passwordId: Long = 0L,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "URL")
    var URL: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "note")
    var note: String
) {
    override fun toString(): String {
        return "$passwordId, $password, $URL, $login, $name"
    }
}