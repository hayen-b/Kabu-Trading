package kabu.hayen.data.repository

import kabu.hayen.data.dataSource.local.AccountDao
import kabu.hayen.data.model.AccountInfo

class AccountRepositoryImpl(private val accountDao: AccountDao): AccountRepository {
    override suspend fun addAccountInfo(accountInfo: AccountInfo) {
        accountDao.addAccountInfo(accountInfo)
    }

    override suspend fun getAccountInfo(username: String): AccountInfo? {
        return accountDao.getAccountInfo(username)
    }
}