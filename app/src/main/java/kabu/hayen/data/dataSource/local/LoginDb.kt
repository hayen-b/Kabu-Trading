package kabu.hayen.data.dataSource.local

import kabu.hayen.data.dataSource.LoginDataSource

class LoginDb: LoginDataSource {
    companion object {
        private val users: List<String> = listOf("hayenb hayen.b2@gmail.com hayen1123")
    }

    override fun checkIfUserExists(username: String, email: String, password: String): Boolean {
        for (user in users) {
            if (username == user.split(" ")[0] &&
                email == user.split(" ")[1] &&
                password == user.split(" ")[2]) {
                return true
            }
        }

        return false
    }
}