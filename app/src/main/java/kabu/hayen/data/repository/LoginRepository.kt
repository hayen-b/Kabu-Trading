package kabu.hayen.data.repository

interface LoginRepository {
    fun checkIfUserExists(username: String, email: String, password: String): Boolean
}