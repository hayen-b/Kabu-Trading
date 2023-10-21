package kabu.hayen.data.repository

import kabu.hayen.data.model.AccountInfo

interface AccountRepository {
    suspend fun addAccountInfo(accountInfo: AccountInfo)
    suspend fun getAccountInfo(username: String): AccountInfo?
}