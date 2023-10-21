package kabu.hayen.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kabu.hayen.data.model.AccountInfo

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccountInfo(accountInfo: AccountInfo)

    @Query("SELECT * FROM account WHERE username = :username")
    suspend fun getAccountInfo(username: String): AccountInfo?
}