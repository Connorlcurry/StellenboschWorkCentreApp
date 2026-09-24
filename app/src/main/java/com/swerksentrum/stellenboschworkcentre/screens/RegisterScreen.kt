package com.swerksentrum.stellenboschworkcentre.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.swerksentrum.stellenboschworkcentre.AuthState
import androidx.compose.ui.unit.dp
import android.R.attr.navigationIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import com.swerksentrum.stellenboschworkcentre.R
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.swerksentrum.stellenboschworkcentre.AuthViewModel
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(

    onNavigateToMain: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToLogin: () -> Unit,
    authViewModel: AuthViewModel


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
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()

            else -> Unit

        }

    }


    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                navigationIcon = {

                    IconButton(onClick = onNavigateUp) {

                        Icon(

                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Arrow Back",
                            tint = Color.Black

                        )

                    }

                },

                title = { Text(text = "Register") }

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

                authViewModel.register(email, password)

            }) {

                Text("Register Account")

            }

            TextButton(onClick = onNavigateToLogin) {

                Text("Already have an account? Login")

            }

        }

    }


}

@Serializable
private data object RegisterDestination


fun NavGraphBuilder.registerScreen(

    onNavigateToMain: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToLogin: () -> Unit

) {

    composable<RegisterDestination> {

        val authViewModel: AuthViewModel = viewModel()

        RegisterScreen(

            onNavigateToMain = onNavigateToMain,
            onNavigateUp = onNavigateUp,
            onNavigateToLogin = onNavigateToLogin,
            authViewModel = authViewModel

        )

    }

}

fun NavController.navigateToRegister() {

    navigate(RegisterDestination)

}