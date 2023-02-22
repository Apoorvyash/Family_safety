//package com.example.familysafety
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface ContactDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(inviteModel: InviteModel)
//
//    @Query("SELECT * FROM inviteModel")
//    suspend fun getAllcontacts(inviteModel: InviteModel)
//
//
//}