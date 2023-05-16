package com.example.familysafety

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(inviteModel: List<com.example.familysafety.InviteModel>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inviteModel: InviteModel)

    @Query("SELECT * FROM inviteModel")
    fun getAllcontacts():LiveData<List<InviteModel>>   //live ek listener main thread pe hi chlta h islie stuck bhi nhi krta background thread pr chlta rhta h
        //jaise hi change hoga , vha pe alert aa jaega ki change aa gya h to ui ko update kr do


}