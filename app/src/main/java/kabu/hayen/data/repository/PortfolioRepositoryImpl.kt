package kabu.hayen.data.repository

import kabu.hayen.data.dataSource.local.PortfolioDao
import kabu.hayen.data.model.PortfolioHistory

class PortfolioRepositoryImpl(private val portfolioDao: PortfolioDao): PortfolioRepository {
    override suspend fun addPortfolioHistory(portfolio: PortfolioHistory) {
        portfolioDao.addPortfolioHistory(portfolio)
    }

    override suspend fun getPortfolioHistory(username: String): List<PortfolioHistory>? {
        return portfolioDao.getPortfolioHistory(username)
    }
}