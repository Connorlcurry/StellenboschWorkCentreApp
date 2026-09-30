package com.swerksentrum.stellenboschworkcentre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel: ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {

        checkAuthStatus()

    }

    fun checkAuthStatus() {

        if(auth.currentUser == null) {

            _authState.value = AuthState.Unauthenticated

        } else {

            _authState.value = AuthState.Authenticated

        }

    }

    fun login(email: String, password: String) {


        if (email.isEmpty() || password.isEmpty()) {

            _authState.value = AuthState.Error("Email or password cannot be empty.")
            return

        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    _authState.value = AuthState.Authenticated

                } else {

                    _authState.value = AuthState.Error(task.exception?.message?: "Something went wrong.")

                }

            }

    }

    fun register(firstName: String, lastName: String, email: String, password: String, phoneNum: String, address: String) {


        if (email.isEmpty() || password.isEmpty()) {

            _authState.value = AuthState.Error("All fields are required.")
            return

        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val userId = auth.currentUser?.uid

                    val userProfile = hashMapOf(

                        "firstName" to firstName,
                        "lastName" to lastName,
                        "email" to email,
                        "password" to password,
                        "phoneNum" to phoneNum,
                        "address" to address

                    )

                    if (userId != null) {

                        db.collection("users").document(userId)
                            .set(userProfile)
                            .addOnSuccessListener {

                                _authState.value = AuthState.Authenticated

                            }
                            .addOnFailureListener {

                                _authState.value = AuthState.Error(it.message ?: "Beneficiary failed to register")

                            }

                    }

                } else {

                    _authState.value = AuthState.Error(task.exception?.message?: "Something went wrong.")

                }

            }

    }

    fun logout() {

        auth.signOut()
        _authState.value = AuthState.Unauthenticated

    }

}

sealed class AuthState {

    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()

}