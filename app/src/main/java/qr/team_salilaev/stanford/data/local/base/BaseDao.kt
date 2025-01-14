package qr.team_salilaev.stanford.data.local.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface BaseDao<ENTITY> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ENTITY)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entity: ENTITY)

    @Delete
    fun delete(entity: ENTITY)

}