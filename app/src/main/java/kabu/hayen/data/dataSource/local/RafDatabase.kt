package kabu.hayen.data.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kabu.hayen.data.model.AccountInfo
import kabu.hayen.data.model.BoughtStock
import kabu.hayen.data.model.PortfolioHistory

@Database(entities = [AccountInfo::class, PortfolioHistory::class, BoughtStock::class],
    version = 1, exportSchema = false
)
abstract class KabuDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun portfolioDao(): PortfolioDao
    abstract fun stocksDao(): StocksDao

    companion object {
        @Volatile
        private var INSTANCE: KabuDatabase? = null

        fun getDatabase(context: Context): KabuDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KabuDatabase::class.java,
                    "kabu_database"
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}