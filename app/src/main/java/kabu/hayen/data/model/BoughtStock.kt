package kabu.hayen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class BoughtStock (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val symbol: String,
    val name: String,
    val price: Double,
    var amount: Int,
    val change: Double
)