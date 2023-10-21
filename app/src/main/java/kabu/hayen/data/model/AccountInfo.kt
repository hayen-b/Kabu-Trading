package kabu.hayen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountInfo (
    @PrimaryKey
    val username: String,
    var balance: Double,
    var portfolio: Double
)