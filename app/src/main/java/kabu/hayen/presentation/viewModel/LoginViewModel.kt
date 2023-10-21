package kabu.hayen.presentation.viewModel

interface LoginViewModel {
    fun validateInput(username: String, email: String, password: String): String?
    fun checkIfUserExists(username: String, email: String, password: String): Boolean
}