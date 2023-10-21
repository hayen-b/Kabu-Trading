package kabu.hayen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class PortfolioHistory (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val value: Double
)