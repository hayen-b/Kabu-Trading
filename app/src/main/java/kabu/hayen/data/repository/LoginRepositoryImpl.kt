package kabu.hayen.data.repository

import kabu.hayen.data.dataSource.LoginDataSource

class LoginRepositoryImpl(private val loginDs: LoginDataSource): LoginRepository {
    override fun checkIfUserExists(username: String, email: String, password: String): Boolean {
        return loginDs.checkIfUserExists(username, email, password)
    }
}