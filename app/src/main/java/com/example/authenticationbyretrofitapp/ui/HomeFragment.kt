package com.example.authenticationbyretrofitapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.authenticationbyretrofitapp.R
import com.example.authenticationbyretrofitapp.api.RetrofitInstance
import com.example.authenticationbyretrofitapp.api.SessionManager
import com.example.authenticationbyretrofitapp.databinding.FragmentHomeBinding
import com.example.authenticationbyretrofitapp.model.UserInfoResponse
import com.example.authenticationbyretrofitapp.utils.Resource
import com.example.authenticationbyretrofitapp.utils.hide
import com.example.authenticationbyretrofitapp.utils.show
import com.example.authenticationbyretrofitapp.viewModel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val authViewModel by viewModels<AuthViewModel>()

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(requireContext(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.customToolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Authentication App"


        val acct = GoogleSignIn.getLastSignedInAccount(requireContext())
        if (acct != null) {
            val name = acct.displayName
            val email = acct.email
//            binding.tvData.text = "name: $name \nemail: $email"
        }

        authViewModel.logoutResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading ->{
                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    // remove token from shared preference
                    SessionManager(requireContext()).removeAuthToken()
                    Toast.makeText(activity, "" + it.data!!.message, Toast.LENGTH_SHORT).show()

                    val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                    mNavController.navigate(action)
                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    Toast.makeText(activity, "" + it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }


        return binding.root
    }


    fun googleSignOut() {
        gsc.signOut().addOnCompleteListener {
            // transfer to login fragment
        }
    }

    private fun logout() {
        authViewModel.logout()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                mNavController.navigate(action)
            }
            R.id.logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}