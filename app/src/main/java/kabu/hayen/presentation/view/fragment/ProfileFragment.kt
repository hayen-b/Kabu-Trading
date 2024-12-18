package kabu.hayen.presentation.view.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kabu.hayen.R
import kabu.hayen.databinding.FragmentProfileBinding
import kabu.hayen.presentation.view.activity.LoginActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()
        return root
    }

    private fun init() {
        val sp: SharedPreferences = requireActivity().getSharedPreferences("userInfo",
            AppCompatActivity.MODE_PRIVATE)
        val username: String? = sp.getString("username", "")
        val email: String? = sp.getString("email", "")

        val usernameTv: TextView = binding.root.findViewById(R.id.usernameRight)
        val emailTv: TextView = binding.root.findViewById(R.id.emailRight)
        usernameTv.text = username
        emailTv.text = email

        val logOutBtn: Button = binding.root.findViewById(R.id.logOutBtn)
        logOutBtn.setOnClickListener {
            val editor: SharedPreferences.Editor = sp.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(activity, LoginActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}