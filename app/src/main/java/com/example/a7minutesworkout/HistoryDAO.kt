package com.example.a7minutesworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("Select * from `history_table`")
    fun fetchAllDates():Flow<List<HistoryEntity>>
}