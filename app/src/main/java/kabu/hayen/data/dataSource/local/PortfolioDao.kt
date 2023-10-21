package kabu.hayen.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kabu.hayen.data.model.PortfolioHistory

@Dao
interface PortfolioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPortfolioHistory(history: PortfolioHistory)

    @Query("SELECT * FROM history WHERE username = :username")
    suspend fun getPortfolioHistory(username: String): List<PortfolioHistory>?
}