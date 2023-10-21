package kabu.hayen.data.dataSource

interface LoginDataSource {
    fun checkIfUserExists(username: String, email: String, password: String): Boolean
}