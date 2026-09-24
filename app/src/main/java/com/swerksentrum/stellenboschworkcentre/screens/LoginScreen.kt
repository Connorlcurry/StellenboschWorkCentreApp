package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import android.widget.Toast
import com.swerksentrum.stellenboschworkcentre.AuthState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(

    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit,
    authViewModel: AuthViewModel,

) {

    var email by remember {

        mutableStateOf("")

    }

    var password by remember {

        mutableStateOf("")

    }

    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {

        when (authState) {

            is AuthState.Authenticated -> onNavigateToMain()
            is AuthState.Error -> Toast.makeText(context,
                (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit

        }

    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = { Text(text = "Login") }

            )

        }

    ) { paddingValues ->

        Column(

            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(

                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }

            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(

                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }

            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                authViewModel.login(email, password)
            }) {

                Text("Login")

            }


            TextButton(onClick = onNavigateToRegister) {

                Text("Don't have an account? Register")

            }


        }


    }

}

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(

    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit

) {

    composable<LoginDestination> {

        val authViewModel: AuthViewModel = viewModel()

        LoginScreen(

            onNavigateToMain = onNavigateToMain,
            onNavigateToRegister = onNavigateToRegister,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToLogin() {

    navigate(LoginDestination)

}