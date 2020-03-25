package com.alkam.passwordgenerator.database

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alkam.passwordgenerator.models.PasswordModel

@Dao
interface PasswordDAO {
    @Insert
    fun insertPassword(password: PasswordModel)

    @Query("SELECT * FROM passwords_table")
    fun getPasswords(): MutableList<PasswordModel>

    @Delete
    fun deletePassword(password: PasswordModel)

}