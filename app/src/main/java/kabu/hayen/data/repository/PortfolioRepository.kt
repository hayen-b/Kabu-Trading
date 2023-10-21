package kabu.hayen.data.repository

import kabu.hayen.data.model.PortfolioHistory

interface PortfolioRepository {
    suspend fun addPortfolioHistory(portfolio: PortfolioHistory)
    suspend fun getPortfolioHistory(username: String): List<PortfolioHistory>?
}