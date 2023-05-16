package com.example.familysafety

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[InviteModel::class], version = 1, exportSchema = false) //export schema se purana data bchana h ya nhi
public abstract class FamilySafetyDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
    //thread safe bnata h , overall khi se bhi thread se use ho skta h


    companion object{
        @Volatile
        private var INSTANCE: FamilySafetyDatabase? =null
        //har jagah use kr skte h app me
        fun getDatabatabase(context: Context): FamilySafetyDatabase {
            INSTANCE?.let {
                return it  //smartcast tb ho skta h null ho jae, to let use krte h to null l=nhi ho to aa jao or copy bna lo ek
            }
            return synchronized(FamilySafetyDatabase::class.java){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FamilySafetyDatabase::class.java,
                    "Apoorv's family database"
                ).build() //ye preferable hota h

                instance
            }

        }
    }
}