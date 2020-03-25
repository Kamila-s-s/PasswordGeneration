package com.alkam.passwordgenerator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alkam.passwordgenerator.models.PasswordModel

@Database(entities = [PasswordModel::class], version = 1)
abstract class PasswordDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: PasswordDatabase? = null

        fun getInstance(context: Context): PasswordDatabase {
            synchronized(this) {
                var instance = Companion.INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PasswordDatabase::class.java, "password_db"
                    )
                        .build()
                    Companion.INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract fun getDao(): PasswordDAO
}